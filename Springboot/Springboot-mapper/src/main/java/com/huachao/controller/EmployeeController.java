package com.huachao.controller;

import com.huachao.pojo.Employee;
import com.huachao.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("list")
    public List<Employee> getList(){
        return employeeService.getList();
    }
}
