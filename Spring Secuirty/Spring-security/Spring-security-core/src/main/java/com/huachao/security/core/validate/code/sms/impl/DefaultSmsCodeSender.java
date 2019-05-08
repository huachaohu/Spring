package com.huachao.security.core.validate.code.sms.impl;

import com.huachao.security.core.validate.code.sms.SmsCodeSender;

public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机:"+mobile+"发送短信验证码:"+code);
    }
}
