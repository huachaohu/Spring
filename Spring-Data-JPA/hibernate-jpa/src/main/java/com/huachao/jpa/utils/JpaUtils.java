package com.huachao.jpa.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/23
 */
public class JpaUtils {
    private static EntityManagerFactory factory;
    static {
        /**
         * 注意：该方法参数必须和persistence.xml中persistence-unit标签name属性取值一致
         */
        factory = Persistence.createEntityManagerFactory("myJpa");
    }
    /**
    * @decription: 使用管理器工厂生产一个管理器对象
    * @param
    * @return javax.persistence.EntityManager
    * @author huachao
    * @date 2020/3/23 21:58
    */
    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
