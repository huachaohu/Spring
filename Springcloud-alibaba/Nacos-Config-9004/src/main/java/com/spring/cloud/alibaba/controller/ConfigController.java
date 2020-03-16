package com.spring.cloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/11
 */
//自动刷新配置
@RefreshScope
@RestController
@RequestMapping("/config")
public class ConfigController {
    @Value("${config.value}")
    private String configVal;

    @GetMapping("/value")
    public String Echo(){
        return configVal;
    }
}
