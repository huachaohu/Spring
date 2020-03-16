package com.spring.cloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/11
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Provider9002 {
    public static void main(String[] args) {
        SpringApplication.run(Provider9002.class , args);
    }
}
