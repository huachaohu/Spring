package com.huachao.rocket.rocket_client.batch;

import com.huachao.rocket.rocket_client.utils.Util;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/7
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        //1.创建消息生产者producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        //2.指定Nameserver地址
        producer.setNamesrvAddr(Util.nsrv);
        //3.启动producer
        producer.start();

        //消息集合
        List<Message> msgs = new ArrayList<Message>();
        //4.创建消息对象，指定主题Topic、Tag和消息体
        /**
         * 参数一：消息主题Topic
         * 参数二：消息Tag
         * 参数三：消息内容
         */
        Message msg1 = new Message("BatchTopic", "batch", ("RocketMQ Sync Send:1" ).getBytes(RemotingHelper.DEFAULT_CHARSET));
        Message msg2 = new Message("BatchTopic", "batch", ("RocketMQ Sync Send:2").getBytes(RemotingHelper.DEFAULT_CHARSET));
        Message msg3 = new Message("BatchTopic", "batch", ("RocketMQ Sync Send:3").getBytes(RemotingHelper.DEFAULT_CHARSET));
        Message msg4 = new Message("BatchTopic", "batch", ("RocketMQ Sync Send:4").getBytes(RemotingHelper.DEFAULT_CHARSET));
        msgs.add(msg1);
        msgs.add(msg2);
        msgs.add(msg3);
        msgs.add(msg4);
        //5.发送消息
        SendResult result = producer.send(msgs);
        // 通过sendResult返回消息是否成功送达
        System.out.printf("%s%n", result);

        producer.shutdown();
    }
}
