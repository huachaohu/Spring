package com.huachao.security.core.properties;

import lombok.Data;

@Data
public class SmsCodeProperties {
    private int length = 6;
    private int expireIn = 40;
    private String url;
}
