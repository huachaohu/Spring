package com.huachao.logs.web;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huachao
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@ComponentScan("com.huachao.logs")
public class WebApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(WebApp.class).run(args);
    }
}
