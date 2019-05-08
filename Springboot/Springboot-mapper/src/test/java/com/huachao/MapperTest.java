package com.huachao;

import com.huachao.entities.Address;
import com.huachao.entities.SeasonEnum;
import com.huachao.entities.User;
import com.huachao.pojo.Employee;
import com.huachao.service.EmployeeService;
import com.huachao.service.UserServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserServices userServices;

    @Test
    public void selectOne(){
        Employee employee = employeeService.selectOne();
        System.out.println(employee);
    }

    @Test
    public void selectByPirmaryKey(){
        Employee employee = employeeService.selectByPrimaryKey();
        System.out.println(employee);
    }

    @Test
    public void delete(){
        Employee e = new Employee(null, "bob", 2209.11, 22);
        employeeService.delete(e);
    }


    @Test
    public void updateBatch(){
        Employee e1 = new Employee(6, "jack1", 2209.11, 23);
        Employee e2 = new Employee(7, "jack2", 22.3, 23);
        Employee e3 = new Employee(8, "zhangsan", 48.55, 31);
        Employee e4 = new Employee(9, "lisi", 128.6, 42);
        List<Employee> list = new ArrayList<>();
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        employeeService.updateBatch(list);
    }

    @Test
    public void testCachNamespace(){
        List<Employee> list = employeeService.getList();
        for (Employee e : list){
            System.out.println(e);
        }

        List<Employee> list2 = employeeService.getList();
        for (Employee e : list2){
            System.out.println(e);
        }
    }

    @Test
    public void testTypeHandlerSave(){
        User u = new User(null,"xxx",new Address("aa1","bb","cc"), SeasonEnum.AUTUMN);
        userServices.saveUser(u);
    }
    @Test
    public void testTypeHandlerQuery(){
        Integer id = 5;
        User user = userServices.getUserById(id);
        System.out.println(user);
    }

}
