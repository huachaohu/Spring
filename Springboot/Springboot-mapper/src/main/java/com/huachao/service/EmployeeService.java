package com.huachao.service;

import com.huachao.mapper.EmployeeMapper;
import com.huachao.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> getList(){
        List<Employee> employees = employeeMapper.selectAll();
        return employees;
    }

    public Employee selectOne(){
        Employee e = new Employee(null,"bob",null,null);
        Employee employee = employeeMapper.selectOne(e);
        return employee;
    }


    public Employee selectByPrimaryKey(){
        Integer id = 3;
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }
    public Employee insert(Employee e){
        employeeMapper.insert(e);
        return e;
    }

    public void delete(Employee e){
        employeeMapper.delete(e);
    }

    public void updateBatch(List<Employee> employees){
        employeeMapper.batchUpdate(employees);
    }
}
