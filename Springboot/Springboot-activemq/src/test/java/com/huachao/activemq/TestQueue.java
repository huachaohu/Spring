package com.huachao.activemq;

import com.huachao.activemq.demo.QueueProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestQueue {
    @Autowired
    private QueueProducer queueProducer;


    @Test
    public void sendTest(){
        queueProducer.sendTextMessage("SpringJms-点对点");
    }

}
