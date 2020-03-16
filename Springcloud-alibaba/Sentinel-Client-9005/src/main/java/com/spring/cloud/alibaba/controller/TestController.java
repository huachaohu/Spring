package com.spring.cloud.alibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/13
 */
@RestController
@Slf4j
public class TestController {
    @RequestMapping("/testA")
    public String testa(){
        log.info("TestA");
        return "TestA";
    }

    @RequestMapping("/testB")
    public String testb(){
        log.info("TestB");
        return "TestB";
    }

    @RequestMapping("/testC")
    public String testc(){
        log.info("TestC");
        return "TestC";
    }

}
