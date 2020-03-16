package com.spring.security.services;

import com.spring.security.model.AuthenticationRequest;
import com.spring.security.model.UserDto;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/16
 */
public interface AuthenticationService {
    /**
     * 用户认证
     * @param authenticationRequest 用户认证请求
     * @return 认证成功的用户信息
     */
    UserDto authentication(AuthenticationRequest authenticationRequest) throws Exception;
}
