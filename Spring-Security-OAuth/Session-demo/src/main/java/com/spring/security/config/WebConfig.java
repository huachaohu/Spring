package com.spring.security.config;

import com.spring.security.interceptor.SimpleAuthenticationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description： 拦截器配置
 * @author: huachao
 * @date: 2020/3/17
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SimpleAuthenticationInterceptor()).addPathPatterns("/r/*");
    }
}
