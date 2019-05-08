package com.huachao.mapper;


import com.huachao.mwMapper.MwMapper;
import com.huachao.pojo.Employee;
import org.apache.ibatis.annotations.CacheNamespace;

@CacheNamespace
public interface EmployeeMapper extends MwMapper<Employee> {
}
