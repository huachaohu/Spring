package com.huachao.core.config;

import com.huachao.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(SecurityProperties.class)
@Configuration
public class Oauth2Config {
}
