package com.spring.cloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/11
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Config9004 {
    public static void main(String[] args) {
        SpringApplication.run(Config9004.class ,args);
    }
}
