package com.huachao.cas.config;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CASConfig {
    //用于单点退出，该过滤器用于实现单点登出功能，可选配置
    @Bean
    public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutHttpSessionListener(){
        ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> listenerRegistration =
                new ServletListenerRegistrationBean<SingleSignOutHttpSessionListener>();
        listenerRegistration.setEnabled(true);
        listenerRegistration.setListener(new SingleSignOutHttpSessionListener());
        listenerRegistration.setOrder(1);
        return listenerRegistration;
    }

    //该过滤器用于实现单点登出功能，可选配置。
    @Bean
    public FilterRegistrationBean<SingleSignOutFilter> singleSignOutFilter(){
        FilterRegistrationBean<SingleSignOutFilter> filterRegistration = new FilterRegistrationBean<SingleSignOutFilter>();
        filterRegistration.setFilter(new SingleSignOutFilter());
        filterRegistration.setEnabled(true);
        filterRegistration.setName("CAS Single Sign Out Filter");
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.addInitParameter("casServerUrlPrefix" , "http://localhost:9100/cas/logout");
        filterRegistration.addInitParameter("serverName","http://localhost:9001");
        filterRegistration.setOrder(2);
        return filterRegistration;
    }
    //该过滤器负责用户的认证工作，必须启用它
    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilter(){
        FilterRegistrationBean<AuthenticationFilter> filterRegistration = new FilterRegistrationBean<AuthenticationFilter>();
        filterRegistration.setEnabled(true);
        filterRegistration.setName("CASFilter");
        filterRegistration.setFilter(new AuthenticationFilter());
        filterRegistration.setOrder(3);
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.addInitParameter("casServerLoginUrl" , "http://localhost:9100/cas/login");
        filterRegistration.addInitParameter("serverName","http://localhost:9001");

        return filterRegistration;
    }

    //该过滤器负责对Ticket 的校验工作，必须启用它
    @Bean
    public FilterRegistrationBean<Cas20ProxyReceivingTicketValidationFilter> cas20ProxyReceivingTicketValidationFilter(){
        FilterRegistrationBean<Cas20ProxyReceivingTicketValidationFilter> filterRegistration = new FilterRegistrationBean<Cas20ProxyReceivingTicketValidationFilter>();

        filterRegistration.setEnabled(true);
        filterRegistration.setName("CAS Validation Filter");
        filterRegistration.setFilter(new Cas20ProxyReceivingTicketValidationFilter());
        filterRegistration.setOrder(4);
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.addInitParameter("casServerUrlPrefix" , "http://localhost:9100/cas");
        filterRegistration.addInitParameter("serverName","http://localhost:9001");
        return filterRegistration;
    }

    //该过滤器负责实现HttpServletRequest 请求的包裹， 比如允许开发者通过
    //HttpServletRequest 的getRemoteUser()方法获得SSO 登录用户的登录名，可选配置。
    @Bean
    public FilterRegistrationBean<HttpServletRequestWrapperFilter> httpServletRequestWrapperFilter(){
        FilterRegistrationBean<HttpServletRequestWrapperFilter> filterRegistration = new FilterRegistrationBean<HttpServletRequestWrapperFilter>();
        filterRegistration.setEnabled(true);
        filterRegistration.setName("CAS HttpServletRequest Wrapper Filter");
        filterRegistration.setFilter(new HttpServletRequestWrapperFilter());
        filterRegistration.setOrder(5);
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }
    //该过滤器使得开发者可以通过org.jasig.cas.client.util.AssertionHolder 来获取用户
    //的登录名。比如AssertionHolder.getAssertion().getPrincipal().getName()。
    @Bean
    public FilterRegistrationBean<AssertionThreadLocalFilter> httpServletRequestWrapperFilterFilterRegistrationBean(){
        FilterRegistrationBean<AssertionThreadLocalFilter> filterRegistration = new FilterRegistrationBean<AssertionThreadLocalFilter>();
        filterRegistration.setEnabled(true);
        filterRegistration.setName("CAS Assertion Thread Local Filter");
        filterRegistration.setFilter(new AssertionThreadLocalFilter());
        filterRegistration.setOrder(6);
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }
}
