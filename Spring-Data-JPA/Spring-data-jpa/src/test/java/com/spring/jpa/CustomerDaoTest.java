package com.spring.jpa;

import com.spring.jpa.model.Customer;
import com.spring.jpa.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerDaoTest {
    @Autowired
    private CustomerRepository customerRepository;

    /**
     * save : 保存或者更新
     *      根据传递的对象是否存在主键id，
     *      如果没有id主键属性：保存
     *      存在id主键属性，根据id查询数据，更新数据
     */
    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setCustAddress("ccc小区");
        customer.setCustIndustry("农业");
        customer.setCustName("不晓得");
        //没有id就是新增，有id就是更新
        customer.setCustId(5l);
        customer.setCustPhone("111111");
        Customer dbCus = customerRepository.save(customer);
        System.out.println(dbCus);
    }

    @Test
    public void testDelete() {
        Customer customer = new Customer();
        customer.setCustId(5l);
        customerRepository.delete(customer);
    }



    @Test
    public void testFindOne() {
        Customer customer = new Customer();
        customer.setCustId(5l);
        Example<Customer> exam = Example.of(customer);
        Optional<Customer> one = customerRepository.findOne(exam);
        if(one.isPresent()){
            System.out.println(one.get());
        }
    }

    @Test
    public void testFindById() {
        Optional<Customer> one = customerRepository.findById(6l);
        System.out.println(one.get());
    }

    /**
     * 查询所有
     */
    @Test
    public void testFindAll() {
        List<Customer> all = customerRepository.findAll();
        System.out.println(all);
    }

    /**
     * 测试统计查询：查询客户的总数量
     *      count:统计总条数
     */
    @Test
    public void testCount() {
        long count = customerRepository.count();
        System.out.println(count);
    }

    /**
     * 测试：判断id为4的客户是否存在
     *      1. 可以查询以下id为4的客户
     *          如果值为空，代表不存在，如果不为空，代表存在
     *      2. 判断数据库中id为4的客户的数量
     *          如果数量为0，代表不存在，如果大于0，代表存在
     */
    @Test
    public void  testExists() {
        Customer customer = new Customer();
        customer.setCustId(6l);
        Example<Customer> exam  = Example.of(customer);
        boolean exists = customerRepository.exists(exam);
        System.out.println("id为6的客户 是否存在："+exists);
    }

    /**
     * 根据id从数据库查询
     *      @Transactional : 保证getOne正常运行
     *
     *  findById：
     *      em.find()           :立即加载
     *  getOne：
     *      em.getReference     :延迟加载
     *      * 返回的是一个客户的动态代理对象
     *      * 什么时候用，什么时候查询
     */
    @Test
    @Transactional
    public void  testGetOne() {
        Customer one = customerRepository.getOne(6l);
        System.out.println(one);
    }

}
