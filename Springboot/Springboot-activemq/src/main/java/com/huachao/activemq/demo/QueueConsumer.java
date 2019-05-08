package com.huachao.activemq.demo;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer{

    /**
     * 收到queue消息后，再次发送queue消息
     * @param text
     * @return
     */
    @JmsListener(destination = "queue_text")
    @SendTo("out.queue")
    public String receiveQueue(String text) {
        System.out.println("queue Consumer 接收到  ： "+text);
        return "queue Consumer 收到："+text;
    }

    @JmsListener(destination = "out.queue")
    public void receiveOutTopic(String text) {
        System.out.println("queue Consumer by out 接收到  ： "+text);
    }
}
