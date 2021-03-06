package com.huachao.rabbitmq.work;

import com.huachao.rabbitmq.utils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * work消息模型
 * 消费者 - 手动ACK
 */
public class Recv2 {
    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道，使用通道才能完成消息相关的操作
        Channel channel = connection.createChannel();
        //声明（创建）队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 设置每个消费者同时只能处理一条消息
        channel.basicQos(1);
        // 定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            // 获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // body 即消息体
                String msg = new String(body);
                System.out.println("消费者2 [x] received : " + msg + "!");
                //手动进行ACK
                channel.basicAck(envelope.getDeliveryTag() , false);
            }
        };
        // 监听队列，第二个参数false，手动进行ACK
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }

}
