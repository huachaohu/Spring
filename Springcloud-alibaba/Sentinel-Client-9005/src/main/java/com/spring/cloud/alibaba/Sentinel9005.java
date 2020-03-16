package com.spring.cloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/12
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Sentinel9005 {
    public static void main(String[] args) {
        SpringApplication.run(Sentinel9005.class , args);
    }
}
