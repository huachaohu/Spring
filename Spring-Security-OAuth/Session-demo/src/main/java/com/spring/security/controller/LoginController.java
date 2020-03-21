package com.spring.security.controller;

import com.spring.security.model.AuthenticationRequest;
import com.spring.security.model.UserDto;
import com.spring.security.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/16
 */
@Controller
public class LoginController {
    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping("/")
    public String index(HttpServletRequest request , String name) {
        request.setAttribute("name" , name);
        return "login";
    }
    @PostMapping(value = "/login")
    @ResponseBody
    public String login(AuthenticationRequest authenticationRequest, HttpSession session) throws Exception {
        UserDto userDetails = authenticationService.authentication(authenticationRequest);
        session.setAttribute(UserDto.SESSION_USER_KEY , userDetails);
        return userDetails.getFullName() + " 登录成功";
    }

    @GetMapping(value = "logout")
    @ResponseBody
    public String logout(HttpSession session){
        session.invalidate();
        return "退出成功";
    }

    /**
     * 测试资源1
     * @param session
     * @return
     */
    @GetMapping(value = "/r/r1")
    @ResponseBody
    public String r1(HttpSession session){
        String fullname = null;
        Object userObj = session.getAttribute(UserDto.SESSION_USER_KEY);
        if(userObj != null){
            fullname = ((UserDto)userObj).getFullName();
        }else{
            fullname = "匿名";
        }
        return fullname + " 访问资源1";
    }
    /**
     * 测试资源2
     * @param session
     * @return
     */
    @GetMapping(value = "/r/r2")
    @ResponseBody
    public String r2(HttpSession session){
        String fullname = null;
        Object userObj = session.getAttribute(UserDto.SESSION_USER_KEY);
        if(userObj != null){
            fullname = ((UserDto)userObj).getFullName();
        }else{
            fullname = "匿名";
        }
        return fullname + " 访问资源2";
    }
}
