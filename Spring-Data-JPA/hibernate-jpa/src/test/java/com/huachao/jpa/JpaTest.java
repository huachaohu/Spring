package com.huachao.jpa;

import com.huachao.jpa.model.Customer;
import com.huachao.jpa.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 测试jpa的保存
 *      案例：保存一个客户到数据库中
 *  Jpa的操作步骤
 *     1.加载配置文件创建工厂（实体管理器工厂）对象
 *     2.通过实体管理器工厂获取实体管理器
 *     3.获取事务对象，开启事务
 *     4.完成增删改查操作
 *     5.提交事务（回滚事务）
 *     6.释放资源
 */
public class JpaTest {
    @Test
    public void testSave(){
        //1.加载配置文件创建工厂（实体管理器工厂）对象
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //2.通过实体管理器工厂获取实体管理器
//        EntityManager manager = factory.createEntityManager();
        EntityManager manager = JpaUtils.getEntityManager();
        //3.获取事务对象，开启事务
        EntityTransaction tx = manager.getTransaction();
        //开启事务
        tx.begin();
        //4.完成增删改查操作：保存一个客户到数据库中
        Customer customer = new Customer();
        customer.setCustAddress("aaa街道");
        customer.setCustName("传智播客");
        customer.setCustIndustry("教育");
        //保存，
        manager.persist(customer); //保存操作
        //5.提交事务
        tx.commit();
        //6.释放资源
        manager.close();
//        factory.close();
    }

    @Test
    public void testFind(){
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        Customer c1 = manager.find(Customer.class, 1l);
        Customer c2 = manager.find(Customer.class, 1l);
        tx.commit();
        manager.close();
        System.out.println(c1);
    }

    @Test
    public void testGetReference(){
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        Customer c1 = manager.getReference(Customer.class, 1l);
        //c1不使用的话，就不会打印sql,说明就不会真正的去查
//        System.out.println(c1);
        tx.commit();
        manager.close();
    }

    @Test
    public void testUpdate(){
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        Customer c1 = manager.find(Customer.class, 1l);
        c1.setCustName("jiaoyu");
        c1.setCustIndustry("hhhhhh");
        manager.merge(c1);
        tx.commit();
        manager.close();
    }

    @Test
    public void testRemove(){
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        Customer c1 = manager.find(Customer.class, 1l);
        manager.remove(c1);
        tx.commit();
        manager.close();
    }
}
