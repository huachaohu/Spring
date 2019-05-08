package com.huachao.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@SpringBootApplication
@Configuration
//@EnableJms
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class , args);
    }

    /**
     * springboot集成activemq的问题： topic和queue不能同时使用
     * springboot 控制使用topic 还是 queue 是通过配置 pub-sub-domain=true 实现的
     * 配置支持topic的Listener
     * @return
     */
    @Bean
    public JmsListenerContainerFactory topicListener(){
        DefaultJmsListenerContainerFactory topicListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        topicListenerContainerFactory.setPubSubDomain(true);
        ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://192.168.175.220:61616");
        topicListenerContainerFactory.setConnectionFactory(factory);
        return  topicListenerContainerFactory;
    }

}
