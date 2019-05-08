package com.huachao.cas.security.config;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CasAuthenticationEntryPoint casProcessingFilterEntryPoint;
    @Autowired
    private LogoutFilter requestSingleLogoutFilter;
    @Autowired
    private SingleSignOutFilter singleLogoutFilter;
    @Autowired
    private CasAuthenticationProvider casAuthenticationProvider;


    /**************************认证过滤器**********************/
    @Bean
    public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
        CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
        //认证管理器
        casAuthenticationFilter.setAuthenticationManager(authenticationManager());
        return casAuthenticationFilter;
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(Arrays.<AuthenticationProvider>asList(casAuthenticationProvider));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().hasRole("USER")
                .and().authorizeRequests()
                .and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(casProcessingFilterEntryPoint)
                .and().addFilter(casAuthenticationFilter())
                .addFilter(requestSingleLogoutFilter)
                .addFilterBefore(singleLogoutFilter ,CasAuthenticationFilter.class);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        //因为设置了退出登录自动跳转到index2.html,所以放行index2.html
        web.ignoring().antMatchers("/index2.html");
        super.configure(web);
    }
}
