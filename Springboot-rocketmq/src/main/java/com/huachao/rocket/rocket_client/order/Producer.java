package com.huachao.rocket.rocket_client.order;

import com.huachao.rocket.rocket_client.model.OrderStep;
import com.huachao.rocket.rocket_client.utils.Util;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

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
        //构建消息集合
        List<OrderStep> orderSteps = OrderStep.buildOrders();
        //发送消息
        for (int i = 0; i < orderSteps.size(); i++) {
            OrderStep orderStep = orderSteps.get(i);
            //4.创建消息对象，指定主题Topic、Tag和消息体
            /**
             * 参数一：消息主题Topic
             * 参数二：消息Tag
             * 参数三：消息内容
             */
            Message message = new Message("OrderTopic", "Order", (orderStep+"").getBytes(RemotingHelper.DEFAULT_CHARSET));
            //5.发送消息
            SendResult result = producer.send(message, new MessageQueueSelector() {
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    long orderId = (Long)o;
                    long index = orderId % list.size();
                    return list.get((int) index);
                }
            } , orderStep.getOrderId());
            // 通过sendResult返回消息是否成功送达
            System.out.printf("%s%n", result);
            //线程睡1秒
            TimeUnit.SECONDS.sleep(1);
        }
        producer.shutdown();
    }
}
