package com.huachao.logs.user;

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
public class UserApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(UserApp.class).run(args);
    }
}
