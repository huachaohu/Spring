package com.huachao.cas.security.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/findLoginUser")
    public String findLoginUser(){
        //当前登录名
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("当前登录名："+name);
        return name;
    }
}
