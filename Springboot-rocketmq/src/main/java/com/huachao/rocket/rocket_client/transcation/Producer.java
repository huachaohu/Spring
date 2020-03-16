package com.huachao.rocket.rocket_client.transcation;

import com.huachao.rocket.rocket_client.utils.Util;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.TimeUnit;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/7
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        //1.创建消息生产者producer，并制定生产者组名
        TransactionMQProducer producer = new TransactionMQProducer("group2");
        //2.指定Nameserver地址
        producer.setNamesrvAddr(Util.nsrv);

        producer.setTransactionListener(new TransactionListener() {
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {
                if(message.getTags().equalsIgnoreCase("TAGA")){//提交
                    return LocalTransactionState.COMMIT_MESSAGE;
                }else if(message.getTags().equalsIgnoreCase("TAGB")){//回滚
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }else{
                    return LocalTransactionState.UNKNOW;
                }
            }

            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {//TAGC的消息会进入回查方法
                System.out.println("回查消息的Tag:" + messageExt.getTags());
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });

        //3.启动producer
        producer.start();

        String[] tags = {"TAGA", "TAGB", "TAGC"};

        for (int i = 0; i < 3; i++) {
            //4.创建消息对象，指定主题Topic、Tag和消息体
            /**
             * 参数一：消息主题Topic
             * 参数二：消息Tag
             * 参数三：消息内容
             */
            Message message = new Message("TranscationTopic", tags[i], ("RocketMQ Transcation Message Send:" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            //5.发送消息
            SendResult result = producer.sendMessageInTransaction(message , null);
            // 通过sendResult返回消息是否成功送达
            System.out.printf("%s%n", result);
            //线程睡1秒
            TimeUnit.SECONDS.sleep(1);
        }
//        producer.shutdown();
    }
}
