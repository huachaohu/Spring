package com.spring.cloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/11
 */
@RestController
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-provider}")
    private String serviceUrl;

    @GetMapping("/echo/{id}")
    public String Echo(@PathVariable("id") String id){
        return restTemplate.getForObject(this.serviceUrl+"/echo/"+id,String.class);
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
