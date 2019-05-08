package com.huachao.cas.security.config;

import com.huachao.cas.security.service.UserDetailServiceImpl;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
public class CASSecurityConfiguration {
    /**************************CAS 入口点**********************/
    @Bean
    @Autowired
    public CasAuthenticationEntryPoint casProcessingFilterEntryPoint(ServiceProperties serviceProperties){
        CasAuthenticationEntryPoint casEntryPoint = new CasAuthenticationEntryPoint();
        casEntryPoint.setLoginUrl("http://localhost:9100/cas/login");
        casEntryPoint.setServiceProperties(serviceProperties);

        return casEntryPoint;
    }
    @Bean
    public ServiceProperties serviceProperties(){
        ServiceProperties serviceProperties = new ServiceProperties();
        //service 配置自身工程的根地址+/login/cas
        serviceProperties.setService("http://localhost:9003/login/cas");
        return serviceProperties;
    }


    /**************************认证过滤器**********************/
    //认证提供者
    @Bean
    @Autowired
    public CasAuthenticationProvider casAuthenticationProvider(ServiceProperties serviceProperties , Cas20ServiceTicketValidator ticketValidator ,UserDetailsByNameServiceWrapper userDetailsByNameServiceWrapper){
        CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
        casAuthenticationProvider.setAuthenticationUserDetailsService(userDetailsByNameServiceWrapper);
        casAuthenticationProvider.setServiceProperties(serviceProperties);
        //ticketValidator 为票据验证器
        casAuthenticationProvider.setTicketValidator(ticketValidator);
        casAuthenticationProvider.setKey("an_id_for_this_auth_provider_only");
        return casAuthenticationProvider;
    }
    //ticketValidator 为票据验证器
    @Bean
    public Cas20ServiceTicketValidator ticketValidator(){
        return new Cas20ServiceTicketValidator("http://localhost:9100/cas");
    }
    @Bean
    @Autowired
    public UserDetailsByNameServiceWrapper userDetailsByNameServiceWrapper(UserDetailServiceImpl userDetailsService){
        UserDetailsByNameServiceWrapper<CasAssertionAuthenticationToken> userDetailsByNameServiceWrapper = new UserDetailsByNameServiceWrapper<CasAssertionAuthenticationToken>(userDetailsService);
        return userDetailsByNameServiceWrapper;
    }

    //认证类
    @Bean
    public UserDetailServiceImpl userDetailsService(){
        return new UserDetailServiceImpl();
    }


    /**************************单点登出**********************/
    @Bean
    public SingleSignOutFilter singleLogoutFilter(){
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setCasServerUrlPrefix("http://localhost:9100/cas/logout");
        singleSignOutFilter.setIgnoreInitConfiguration(true);
        return singleSignOutFilter;
    }

    @Bean
    @Autowired
    public LogoutFilter requestSingleLogoutFilter(SecurityContextLogoutHandler securityContextLogoutHandler){
        LogoutFilter logoutFilter = new LogoutFilter("http://localhost:9100/cas/logout?service=http://localhost:9003/index2.html", securityContextLogoutHandler);
        logoutFilter.setFilterProcessesUrl("/logout/cas");
        return logoutFilter;
    }

    @Bean
    public SecurityContextLogoutHandler securityContextLogoutHandler(){
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        return securityContextLogoutHandler;
    }
}
