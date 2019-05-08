package com.huachao;


import com.huachao.pojo.Employee;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QBCtest {

    public void testSelectByExample(){
        Example example = new Example(Employee.class);
    }

}
