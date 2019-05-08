package com.huachao.security.core.validate.code.generator.imp;

import com.huachao.security.core.properties.SecurityProperties;
import com.huachao.security.core.validate.code.entities.ImageCode;
import com.huachao.security.core.validate.code.entities.ValidateCode;
import com.huachao.security.core.validate.code.generator.ValidateCodeGenerator;
import lombok.Setter;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {
    @Setter
    private SecurityProperties securityProperties;

    /**
     * 生成短信验证码
     * @param request
     * @return
     */
    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code , securityProperties.getCode().getSms().getExpireIn());
    }
}
