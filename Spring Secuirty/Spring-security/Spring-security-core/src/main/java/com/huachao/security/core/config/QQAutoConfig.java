package com.huachao.security.core.config;

import com.huachao.security.core.properties.QQProperties;
import com.huachao.security.core.properties.SecurityProperties;
import com.huachao.security.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

@Configuration
//只有application.yml（或application.properties）文件中配置了该属性，才生效
@ConditionalOnProperty(prefix = "huachao.security.social.qq" ,name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig = securityProperties.getSocial().getQq();
        String providerId = qqConfig.getProviderId();
        String appId = qqConfig.getAppId();
        String appSecret = qqConfig.getAppSecret();
        return new QQConnectionFactory(providerId , appId , appSecret);
    }
}
