package com.huachao.demo.web.controller;

import com.huachao.demo.dto.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("user")
    public List<User> list(){
        List<User> list = new ArrayList<>();
        list.add(new User(1,"jack",24));
        list.add(new User(2,"marry",25));
        list.add(new User(3,"lili",26));
        return list;
    }

    //@RequestMapping(value = "user/{id:\d+}",method = RequestMethod.GET)
    @GetMapping("user/{id:\\d+}")
    @ApiOperation(value = "根据id查询用户") //方法的描述
    public User getUser(@ApiParam("用户id") @PathVariable String id){
        System.out.println(id);
        return new User(1,"lili",26);
    }

    @GetMapping("/me")
    public Object getUserInfo(Authentication authentication){
        return authentication;
    }
}
