package com.spring.security.uaa.model;

import lombok.Data;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/21
 */
@Data
public class UserDto {
    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;
}
