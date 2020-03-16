package com.spring.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/16
 */
@Data
@AllArgsConstructor
public class UserDto {
    public static final String SESSION_USER_KEY = "_user";

    private String id;
    private String userName;
    private String password;
    private String fullName;
    private String mobile;

    /**
     * 用户权限
     */
    private Set<String> authorities;
}
