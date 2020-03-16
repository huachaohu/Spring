package com.huachao.rocket.rocket_client.base.producer;

import com.huachao.rocket.rocket_client.utils.Util;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.TimeUnit;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/7
 */
public class AsyncProducer {
    public static void main(String[] args) throws Exception {
        //1.创建消息生产者producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        //2.指定Nameserver地址
        producer.setNamesrvAddr(Util.nsrv);
        //3.启动producer
        producer.start();

        for (int i = 0; i < 5; i++) {
            //4.创建消息对象，指定主题Topic、Tag和消息体
            Message message = new Message("base1", "Tag2", ("RocketMQ Async Send:" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            //5.发送异步消息
            producer.send(message, new SendCallback() {
                /**
                * @decription: 发送成功回调函数
                * @param sendResult
                * @return void
                * @author huachao
                * @date 2020/3/7 17:48
                */
                public void onSuccess(SendResult sendResult) {
                    System.out.println("发送结果：" + sendResult);
                }
                /**
                * @decription: 发送失败回调函数
                * @param e
                * @return void
                * @author huachao
                * @date 2020/3/7 17:48
                */
                public void onException(Throwable e) {
                    System.out.println("发送异常：" + e);
                }
            });

            //线程睡1秒
            TimeUnit.SECONDS.sleep(5);

        }
        //6.关闭生产者producer
        producer.shutdown();
    }
}
