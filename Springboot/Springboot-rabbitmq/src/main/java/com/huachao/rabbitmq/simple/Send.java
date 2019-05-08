package com.huachao.rabbitmq.simple;

import com.huachao.rabbitmq.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 简单模式
 * 发送消息
 */
public class Send {
    //队列的名字
    private static final String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道，使用通道才能完成消息相关的操作
        Channel channel = connection.createChannel();
        //声明（创建）队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //消息内容
        String message = "Hello Rabbitmq!";
        // 向指定的队列中发送消息
        channel.basicPublish("",QUEUE_NAME , null , message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
