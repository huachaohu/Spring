package com.huachao.activemq.controller;

import com.huachao.activemq.demo.QueueProducer;
import com.huachao.activemq.demo.TopicProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activemq")
public class ProducerController {
    @Autowired
    private QueueProducer queueProducer;
    @Autowired
    private TopicProducer topicProducer;


    @RequestMapping("/send/queue")
    public String sendMsgByQueue(String msg){
        queueProducer.sendTextMessage(msg);
        return "发送queue成功";
    }

    @RequestMapping("/send/topic")
    public String sendMsgByTopic(String msg){
        topicProducer.sendTextMessage(msg);
        return "发送topic成功";
    }

}
