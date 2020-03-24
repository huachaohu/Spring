package com.spring.security.uaa.service;

import com.spring.security.uaa.dao.UserDao;
import com.spring.security.uaa.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/21
 */
@Service
@Slf4j
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    UserDao userDao;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username="+username);
        UserDto user = userDao.getUserByUsername(username);
        if(user == null){ return null; }
        //这里暂时使用静态数据
        UserDetails userDetails = User.withUsername(user.getFullname()).password(user.getPassword()).authorities("p1").build();
        return userDetails;
    }
}
