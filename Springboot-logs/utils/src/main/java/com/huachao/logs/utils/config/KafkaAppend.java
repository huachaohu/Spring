package com.huachao.logs.utils.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import lombok.Data;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huachao
 */
@Data
public class KafkaAppend extends AppenderBase<ILoggingEvent> {
    private String topic,brokers;

    private KafkaTemplate kafkaTemplate;

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        kafkaTemplate.send(topic , iLoggingEvent.getMessage());
    }

    @Override
    public void start() {
        Map<String, Object> props = new HashMap<>();
        //连接地址
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,brokers);
        //重试，0为不启⽤重试机制
        props.put(ProducerConfig.RETRIES_CONFIG,1);
        //控制批处理⼤⼩，单位为字节
        props.put(ProducerConfig.BATCH_SIZE_CONFIG,10240);
        //批量发送，延迟为1毫秒，启⽤该功能能有效减少⽣产者发送消息次数，从⽽提⾼并发量
        props.put(ProducerConfig.LINGER_MS_CONFIG,1);
        //⽣产者可以使⽤的最⼤内存字节来缓冲等待发送到服务器的记录
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG,1024000);
        //键的序列化⽅式
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //值的序列化⽅式
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        kafkaTemplate = new KafkaTemplate<String , String>(new DefaultKafkaProducerFactory<>(props));
        super.start();
    }
}
