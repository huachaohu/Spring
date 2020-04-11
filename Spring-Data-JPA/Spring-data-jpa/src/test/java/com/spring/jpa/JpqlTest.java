package com.spring.jpa;

import com.spring.jpa.model.Customer;
import com.spring.jpa.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class JpqlTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void findByCustName(){
        Customer customer = customerRepository.findByCustNameForJpql("传智播客");
        System.out.println(customer);
    }

    @Test
    public void findByCustNameAndIndustry(){
        Customer customer = customerRepository.findByCustNameAndIndustry("教育","传智播客");
        System.out.println(customer);
    }

    /**
     * 测试jpql的更新操作
     *  * springDataJpa中使用jpql完成 更新/删除操作
     *         * 需要手动添加事务的支持
     *         * 默认会执行结束之后，回滚事务
     *   @Rollback : 设置是否自动回滚
     *          false | true
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testUpdateCustomer() {
        customerRepository.updateCustomer(6l,"黑马程序员");
    }

    /**
     * 测试sql查询
     */
    @Test
    public void testFindSql() {
        List<Object[]> list = customerRepository.findBySql("传智播客%");
        for (Object[] obj : list) {
            System.out.println(Arrays.toString(obj));
        }
    }

    /**
     * 测试方法命名规则的查询
     */
    @Test
    public void testNaming() {
        Customer customer = customerRepository.findByCustName("黑马程序员");
        System.out.println(customer);
    }
}
