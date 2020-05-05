package com.huachao.logs.user.controller;

import com.huachao.logs.utils.model.LogBean;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huachao
 */
@RestController
public class LogController {
    private final Logger log = LoggerFactory.getLogger("kafka");
    @RequestMapping("print")
    public Object printLog(){
//        LogBean logBean = new LogBean(String.valueOf(RandomUtils.nextLong()),"session"+RandomUtils.nextInt() , "iphone" , "java:user" ,"I am nginx");
//        log.info(logBean.toString());
        return "he he he";
    }
}