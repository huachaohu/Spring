package com.huachao.activemq.demo;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumer {
    @JmsListener(destination = "topic_text" , containerFactory = "topicListener")
    public void receiveMessage(String text){
        System.out.println("Topic Consumer收到:"+text);
    }
}
