package com.huachao.activemq.test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueProducer {
    public static void main(String[] args) {
        try {
            //1. 创建连接工厂
            ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.175.220:61616");
            //2. 创建连接
            Connection connection = factory.createConnection();
            //3. 启动连接
            connection.start();
            //4. 获取session(会话对象) ，
            // 参数一：是否启用事务
            // 参数二：消息自动确认
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //5. 创建队列对象
            Queue queue = session.createQueue("test-queue");
            //6. 创建消息生产者
            MessageProducer producer = session.createProducer(queue);
            //7. 创建消息对象(文本消息)
            TextMessage message = session.createTextMessage("欢迎来到神奇的品优购世界~~~~~~");
            //8. 发送消息
            producer.send(message);
            //9.关闭资源
            producer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
