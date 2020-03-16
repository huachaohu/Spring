package com.spring.security.services.impl;

import com.spring.security.model.AuthenticationRequest;
import com.spring.security.model.UserDto;
import com.spring.security.services.AuthenticationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/16
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    public UserDto authentication(AuthenticationRequest authenticationRequest) throws Exception {
        if(authenticationRequest == null
                || StringUtils.isEmpty(authenticationRequest.getUsername())
                || StringUtils.isEmpty(authenticationRequest.getPassword())){
            throw new RuntimeException("账号或密码为空");
        }
        UserDto userDto = getUserDto(authenticationRequest.getUsername());
        if(userDto==null){
            throw new RuntimeException("查询不到该用户");
        }
        if(!authenticationRequest.getPassword().equals(userDto.getPassword())){
            throw new RuntimeException("账号或密码错误");
        }
        return userDto;
    }

    //模拟用户查询
    public UserDto getUserDto(String username){
        return userMap.get(username);
    }

    //用户信息
    Map<String,UserDto> userMap = new HashMap(){
    {
        Set<String> authorities1 = new HashSet();
        authorities1.add("p1");
        Set<String> authorities2 = new HashSet();
        authorities2.add("p2");
        put("zhangsan",new UserDto("1010","zhangsan","123","张三","133443" , authorities1));
        put("lisi",new UserDto("1011","lisi","456","李四","144553" , authorities2));
    }};
}
