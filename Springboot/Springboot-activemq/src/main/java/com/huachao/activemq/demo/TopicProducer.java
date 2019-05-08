package com.huachao.activemq.demo;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

@Component
public class TopicProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendTextMessage(String text){
        Destination destination = new ActiveMQTopic("topic_text");
        jmsMessagingTemplate.convertAndSend(destination ,text);
    }
}
