package com.huachao.jpa;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @description：
 * @author: huachao
 * @date: 2020/1/7
 */
public class Test01 {
    @Test
    public void test(){
        System.out.println("---->");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();

        tx.begin();
//        manager.persist();

    }
}
