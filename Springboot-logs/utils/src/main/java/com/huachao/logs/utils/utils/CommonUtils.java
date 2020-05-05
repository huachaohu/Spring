package com.huachao.logs.utils.utils;

import org.apache.commons.lang3.RandomUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author huachao
 */
public class CommonUtils {
    /**
     * 获取rid(request_id),使用随机字符串
     * @param len
     * @return
     */
    public static String getRandomStr(int len){
        char[] chars="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = chars[RandomUtils.nextInt(0,61)];
        }
        return new String(str);
    }

    /**
     * 获取tid,设备信息
     * @param userAgent
     * @return
     */
    public static String getDevice(String userAgent){
        String[] terminals = {
                "iphone", "android", "touch", "ipad", "symbian", "htc", "palmos",
                "blackberry", "opera mini", "windows ce", "nokia", "fennec","macintosh",
                "hiptop", "kindle", "mot", "webos", "samsung", "sonyericsson",
                "wap", "avantgo", "eudoraweb", "minimo", "netfront", "teleca","windows nt"
        };
        userAgent = userAgent.toLowerCase();
        for (String terminal : terminals) {
            if (userAgent.indexOf(terminal) != -1){
                return terminal;
            }
        }
        return userAgent;
    }

    /**
     * 获取ip
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    //测试：
    public static void main(String[] args) {
        /*for (int i = 0; i < 5; i++) {
            System.out.println(getRandomStr(10));
        }*/
        /*
        String userAgent = "ozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.92 Safari/537.36";
        System.out.println(getDevice(userAgent));
        */
    }
}
