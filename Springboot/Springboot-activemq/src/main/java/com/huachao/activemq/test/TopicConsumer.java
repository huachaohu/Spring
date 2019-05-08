package com.huachao.activemq.test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class TopicConsumer {

    public static void main(String[] args) {
        //1. 创建连接工厂
        try {
            ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.175.220:61616");
            //2. 创建连接
            Connection connection  = factory.createConnection();
            //3. 启动连接
            connection.start();
            //4. 获取session(会话对象) ，
            // 参数一：是否启用事务
            // 参数二：消息自动确认
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //5. 创建Topic对象
            Topic topic = session.createTopic("test-topic");
            //6.创建消息消费对象
            MessageConsumer consumer = session.createConsumer(topic);
            //7.监听消息
            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("接收到消息:" + textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

            //8.等待键盘输入
            System.in.read();
            //9.关闭资源
            consumer.close();
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
