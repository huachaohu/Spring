package com.huachao.security.core.properties;

import lombok.Data;

@Data
public class BrowserProperties {

    private String loginPage = "/security-signIn.html";
    private String signUpUrl = "/security-signUp.html";

    private LoginType loginType = LoginType.JSON;

    private int rememberMeSeconds = 3600;

}
