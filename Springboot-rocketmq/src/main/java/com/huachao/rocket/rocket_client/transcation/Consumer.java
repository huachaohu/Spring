package com.huachao.rocket.rocket_client.transcation;

import com.huachao.rocket.rocket_client.utils.Util;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/7
 */
public class Consumer {
    public static void main(String[] args) throws Exception {
        //1.创建消费者Consumer，制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group2");
        //2.指定Nameserver地址
        consumer.setNamesrvAddr(Util.nsrv);
        //3.订阅主题Topic和Tag
        consumer.subscribe("TranscationTopic" ,"*");

        //设定消费模式：负载均衡|广播模式
//        consumer.setMessageModel(MessageModel.CLUSTERING);

        //4.注册回调函数，处理消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt msg : list) {
                    System.out.println("consumeThread=" + Thread.currentThread().getName() + "," + new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        //启动消息者
        consumer.start();
        System.out.printf("Consumer Started......%n");

    }
}
