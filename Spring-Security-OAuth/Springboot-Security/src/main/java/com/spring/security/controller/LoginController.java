package com.spring.security.controller;

import com.spring.security.model.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/17
 */
@Controller
public class LoginController {
    @GetMapping("/")
    public String login() {
        return "redirect:/login";
    }

    @RequestMapping("/loginSuccess")
    @ResponseBody
    public String success() {
        return "success";
    }

    /**
     * 测试资源1
     * @param session
     * @return
     */
    @GetMapping(value = "/r/r1")
    @ResponseBody
    public String r1(HttpSession session){
        return " 访问资源1";
    }
    /**
     * 测试资源2
     * @param session
     * @return
     */
    @GetMapping(value = "/r/r2")
    @ResponseBody
    public String r2(HttpSession session){
        return " 访问资源2";
    }
}
