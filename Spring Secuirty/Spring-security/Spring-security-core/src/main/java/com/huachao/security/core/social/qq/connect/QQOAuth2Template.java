package com.huachao.security.core.social.qq.connect;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Slf4j
public class QQOAuth2Template extends OAuth2Template {

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String result = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
        log.info("获取accessToke的响应："+result);
        //qq的返回类型：access_token=FE04************************CCE2&expires_in=7776000&refresh_token=88E4************************BE14
        String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(result, "&");
        String access_token = StringUtils.substringAfter(items[0], "=");
        Long expires_in = new Long(StringUtils.substringAfter(items[1], "="));
        String refresh_token = StringUtils.substringAfter(items[2], "=");

        return new AccessGrant(access_token , null, refresh_token , expires_in);
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }
}
