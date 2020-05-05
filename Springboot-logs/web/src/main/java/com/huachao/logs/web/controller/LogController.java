package com.huachao.logs.web.controller;

import com.huachao.logs.utils.model.LogBean;
import com.huachao.logs.web.service.ApiService;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huachao
 */
@RestController
@RequestMapping("/api")
public class LogController {
    private final Logger logger = LoggerFactory.getLogger("kafka");
    @Autowired
    private ApiService apiService;

    @RequestMapping("print")
    public Object printLog(){
        LogBean logBean = LogBean.threadLocal.get();
        logBean.setMessage("I am Controller");
        logger.info(logBean.toString());
        apiService.print();
        return "ha ha ha";
    }

    @RequestMapping("info")
    public Object info(){
        LogBean logBean = LogBean.threadLocal.get();
        logBean.setMessage("I am web.Controller");
        logger.info(logBean.toString());
        apiService.info();
        return "ha ha ha";
    }
}
