package com.huachao.rabbitmq.topic;

import com.huachao.rabbitmq.utils.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * Direct模式
 * 发送消息
 */
public class Send {
    private final static String EXCHANGE_NAME = "topic_exchange_test";

    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道，使用通道才能完成消息相关的操作
        Channel channel = connection.createChannel();
        //声明（创建）队列
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        /*****************************发送三次消息，routingKey分别为：insert、update、delete********************************/
        //消息内容
        String message1 = "商品新增了， id = 1001";
        // 发送消息，并且指定routing key 为：insert ,代表新增商品
        channel.basicPublish(EXCHANGE_NAME,"item.insert" , null , message1.getBytes());
        System.out.println("[商品服务：][x] Sent '" + message1 + "'");

        String message2 = "商品修改了， id = 1001";
        // 发送消息，并且指定routing key 为：insert ,代表新增商品
        channel.basicPublish(EXCHANGE_NAME,"item.update" , null , message2.getBytes());
        System.out.println("[商品服务：][x] Sent '" + message2 + "'");

        String message3 = "商品删除了， id = 1001";
        // 发送消息，并且指定routing key 为：insert ,代表新增商品
        channel.basicPublish(EXCHANGE_NAME,"item.delete" , null , message3.getBytes());
        System.out.println("[商品服务：][x] Sent '" + message3 + "'");
        /*****************************发送三次消息，routingKey分别为：insert、update、delete********************************/


        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
