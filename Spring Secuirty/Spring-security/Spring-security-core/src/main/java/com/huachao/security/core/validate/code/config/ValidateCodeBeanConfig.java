package com.huachao.security.core.validate.code.config;

import com.huachao.security.core.properties.SecurityProperties;
import com.huachao.security.core.validate.code.generator.ValidateCodeGenerator;
import com.huachao.security.core.validate.code.generator.imp.ImageCodeGenerator;
import com.huachao.security.core.validate.code.sms.SmsCodeSender;
import com.huachao.security.core.validate.code.sms.impl.DefaultSmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeBeanConfig {
    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")//当spring容器没有名称为imageCodeGenerator的bean时，才创建
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(name = "smsCodeSender")//当spring容器没有名称为imageCodeGenerator的bean时，才创建
    public SmsCodeSender smsCodeGenerator(){
        return new DefaultSmsCodeSender();
    }
}
