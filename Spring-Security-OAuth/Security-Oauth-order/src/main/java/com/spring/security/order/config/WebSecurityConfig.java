package com.spring.security.order.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/21
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() .authorizeRequests()
                // 所有/r/**的请求必须认证通过
                .antMatchers("/r/**").authenticated()
                //除了/r/**，其它的请求可以访问
                .anyRequest().permitAll();
    }
}
