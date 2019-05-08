package com.huachao.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {
    private static final  String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    private static final  String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;
    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String accessToken, String appId) {
        //TokenStrategy.ACCESS_TOKEN_PARAMETER的意思是将access_token参数挂载在请求中，
        // 所以我们在下面的getUserInfo方法中，可以不用传递access_token参数
        //如果不使用这个构造函数，默认是将access_token参数挂载在header中
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;

        String url = String.format(URL_GET_OPENID , accessToken);
        String result = getRestTemplate().getForObject(url,String.class);
        this.openId = StringUtils.substringBetween(result , "openid\":\"" , "\"}");

    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USERINFO, this.appId, this.openId);

        String result = getRestTemplate().getForObject(url, String.class);
        log.info(result);
        QQUserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(result, QQUserInfo.class);
            userInfo.setOpenId(openId);

            return userInfo;
        } catch (Exception e) {
            throw new RuntimeException("获取用户信息失败", e);
        }
    }
}
