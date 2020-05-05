package com.huachao.logs.user.controller;

import com.huachao.logs.utils.model.LogBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huachao
 */
@RestController
public class InfoController {
    private final Logger log = LoggerFactory.getLogger("kafka");
    @RequestMapping("info")
    public Object printLog(){

        LogBean logBean = LogBean.threadLocal.get();
        logBean.setMessage("I am user.controller");
        log.info(logBean.toString());
        return "zhangsan";
    }
}
