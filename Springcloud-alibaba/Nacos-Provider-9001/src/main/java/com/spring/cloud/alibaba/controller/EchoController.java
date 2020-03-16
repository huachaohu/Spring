package com.spring.cloud.alibaba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/11
 */
@RestController
public class EchoController {
    @GetMapping(value = "/echo/{id}")
    public String echo(@PathVariable String id) {
        return "Hello 9001 Nacos Discovery " + id;
    }
}
