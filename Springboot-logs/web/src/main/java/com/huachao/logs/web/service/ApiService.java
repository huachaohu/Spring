package com.huachao.logs.web.service;

import com.huachao.logs.utils.annotation.LogInfo;
import com.huachao.logs.utils.model.LogBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author huachao
 */
@Service
public class ApiService {
    private final static Logger logger = LoggerFactory.getLogger("kafka");

    @Autowired
    private RestTemplate myRestTemplate;


    @LogInfo
    public void print(){
        LogBean logBean = LogBean.threadLocal.get();
        logBean.setMessage("I am service");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(logBean.toString());
    }

    @LogInfo
    public void info(){
        LogBean logBean = LogBean.threadLocal.get();
        logBean.setMessage("I am web.service");
        logger.info(logBean.toString());
        logBean.setMessage("before call user.info");
        logger.info(logBean.toString());

        String res = myRestTemplate.getForObject("http://user/info", String.class);

        logBean.setMessage("after call user, res="+res);
        logger.info(logBean.toString());
    }
}
