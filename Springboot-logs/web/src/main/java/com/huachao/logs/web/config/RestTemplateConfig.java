package com.huachao.logs.web.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author huachao
 */
@Configuration
public class RestTemplateConfig {
    @Bean(name = "myRestTemplate")
    @LoadBalanced
    public RestTemplate myRestTemplate(){
        return new MyRestTemplate();
    }
}
