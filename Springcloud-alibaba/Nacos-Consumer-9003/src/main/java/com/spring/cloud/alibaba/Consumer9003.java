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
public class Consumer9003 {
    public static void main(String[] args) {
        SpringApplication.run(Consumer9003.class ,args);
    }
}
