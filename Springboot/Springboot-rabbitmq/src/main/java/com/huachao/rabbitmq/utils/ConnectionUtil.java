package com.huachao.rabbitmq.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {

    public static Connection getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务器地址
        factory.setHost("node2.mywaytec.cn");
        //端口
        factory.setPort(5672);
        //设置账号信息，用户名、密码、vhost
        factory.setUsername("leyou");
        factory.setPassword("leyou");
        factory.setVirtualHost("/leyou");
        // 通过工程获取连接
        Connection connection = factory.newConnection();

        return connection;
    }
}
