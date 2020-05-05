package com.huachao.logs.utils.filter;


import com.huachao.logs.utils.model.LogBean;
import com.huachao.logs.utils.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author huachao
 */
@Configuration
@WebFilter(filterName = "logFilter" , urlPatterns = "/*")
@Order(1)
public class LogFilter implements Filter {

    //知识点：被哪个app引⽤，当前from的日志记录就是当前app的名字
    @Value("${spring.application.name}")
    private String appName;
    //知识点：slf4j的好处，utils被其他项目引用时不会给对⽅的⽇志产⽣⼲扰
    private Logger log = LoggerFactory.getLogger("kafka");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        //跨服务调用，直接从header里面取
        String rid = StringUtils.defaultIfBlank(httpRequest.getHeader("rid") , CommonUtils.getRandomStr(10));
        String sid = StringUtils.defaultIfBlank(httpRequest.getHeader("sid") , CommonUtils.getRandomStr(10));
        String tid = StringUtils.defaultIfBlank(httpRequest.getHeader("tid") , CommonUtils.getDevice(httpRequest.getHeader("User-Agent")));
        String ip =  StringUtils.defaultIfBlank(httpRequest.getHeader("ip") , CommonUtils.getIpAddress(httpRequest));
        LogBean logBean = new LogBean(rid, sid, tid, appName, "I am filter" , ip ,"java:"+httpRequest.getRequestURI());
        log.info(logBean.toString());
        filterChain.doFilter(request ,response);
    }
}
