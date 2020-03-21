package com.spring.security.model;

import lombok.Data;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/16
 */
@Data
public class AuthenticationRequest {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
