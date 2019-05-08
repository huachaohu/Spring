package com.huachao.security.core.validate.code.filter;

import com.huachao.security.core.properties.SecurityProperties;
import com.huachao.security.core.validate.code.entities.ImageCode;
import com.huachao.security.core.validate.code.exception.ValidateCodeException;
import com.huachao.security.core.validate.code.web.ValidateCodeController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {
    /**
     * 验证码校验失败处理器
     */
    private AuthenticationFailureHandler authenticationFailureHandler;
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 系统配置信息
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 验证请求url与配置的url是否匹配的工具类
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    private Set<String> urls = new HashSet<>();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        System.out.println("----------------InitializingBean----------------");

        String urlString = securityProperties.getCode().getImage().getUrl();
        if(StringUtils.isNotBlank(urlString)){
            String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
            for (String url : configUrls){
                urls.add(url);
            }
            //登录的请求一定需要验证码
            urls.add("/authentication/form");
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean isMatched = false;
        for (String url : urls){
            if(pathMatcher.match(url , request.getRequestURI())){
                isMatched = true;
            }
        }
        if(isMatched){
            try {
                validate(new ServletWebRequest(request));
                logger.info("验证码校验通过");
            }catch (ValidateCodeException e){
                authenticationFailureHandler.onAuthenticationFailure(request,response ,e);
                return;
            }
        }
        filterChain.doFilter(request,response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        ImageCode imageCodeSession = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);
        String imageCode = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

        if(StringUtils.isBlank(imageCode)){
            throw new ValidateCodeException("验证码不能为空");
        }

        if(imageCodeSession == null){
            throw new ValidateCodeException("验证码不存在");
        }

        if(imageCodeSession.isExpried()){
            throw new ValidateCodeException("验证码已过期");
        }

        if(!StringUtils.equals(imageCodeSession.getCode() , imageCode)){
            throw new ValidateCodeException("验证码不匹配");
        }
        //删除session中的验证码
        sessionStrategy.removeAttribute(request ,ValidateCodeController.SESSION_KEY);
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
