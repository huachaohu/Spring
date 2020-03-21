package com.spring.security.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/21
 */
@Configuration
public class TokenConfig {
    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }
}
