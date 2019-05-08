package com.huachao.activemq.demo;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

@Component
public class QueueProducer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 发送消息
     * @param text
     */
    public void sendTextMessage(String text){
        Destination destination = new ActiveMQQueue("queue_text");
        jmsMessagingTemplate.convertAndSend(destination , text);
    }
}
