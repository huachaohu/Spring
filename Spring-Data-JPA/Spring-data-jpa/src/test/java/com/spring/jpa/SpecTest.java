package com.spring.jpa;

import com.spring.jpa.repository.CustomerDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/28
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpecTest {
    @Autowired
    private CustomerDao customerDao;

    /**
     * 根据条件，查询单个对象
     *
     */
    @Test
    public void testSpec() {


    }
}
