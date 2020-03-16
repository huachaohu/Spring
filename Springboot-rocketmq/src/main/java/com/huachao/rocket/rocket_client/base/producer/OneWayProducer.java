package com.huachao.rocket.rocket_client.base.producer;

import com.huachao.rocket.rocket_client.utils.Util;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.TimeUnit;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/7
 */
public class OneWayProducer {
    public static void main(String[] args) throws Exception {
        //1.创建消息生产者producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        //2.指定Nameserver地址
        producer.setNamesrvAddr(Util.nsrv);
        //3.启动producer
        producer.start();
        for (int i = 0; i < 5; i++) {
            //4.创建消息对象，指定主题Topic、Tag和消息体
            /**
             * 参数一：消息主题Topic
             * 参数二：消息Tag
             * 参数三：消息内容
             */
            Message message = new Message("base1", "Tag1", ("RocketMQ OneWay Send:" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            //5.发送单向消息
            producer.sendOneway(message);

            //线程睡1秒
            TimeUnit.SECONDS.sleep(3);
        }
        producer.shutdown();
    }
}
