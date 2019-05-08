package com.huachao.handlers;

import com.huachao.entities.Address;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(value = Address.class)
public class AddressTypeHandler extends BaseTypeHandler<Address> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Address address, JdbcType jdbcType) throws SQLException {
        //1.对address对象进行验证
        if(address == null) return;
        //2.从address对象中取出具体数据
        String city = address.getCity();
        String province = address.getProvince();
        String street = address.getStreet();

        //3.拼装成一个字符串
        //规则：各个值之间使用“,”分开
        StringBuilder builder = new StringBuilder();
        builder.append(city)
                .append(",")
                .append(province)
                .append(",")
                .append(street);
        //4.设置参数
        preparedStatement.setString(i,builder.toString());
    }

    @Override
    public Address getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        //1.根据字段名从rs对象中获取字段值
        String columnValue = resultSet.getString(columnName);

        return getAddressByColumValue(columnValue);
    }

    @Override
    public Address getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        //1.根据字段名从rs对象中获取字段值
        String columnValue = resultSet.getString(columnIndex);
        return getAddressByColumValue(columnValue);
    }

    @Override
    public Address getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        //1.根据字段名从rs对象中获取字段值
        String columnValue = callableStatement.getString(columnIndex);
        return getAddressByColumValue(columnValue);
    }

    private Address getAddressByColumValue(String columnValue){
        //2.验证columnValue是否有效
        if(columnValue == null || columnValue.length()<=0 || !columnValue.contains(","))
            return null;
        //3.根据“,”对columnValue进行拆分
        String[] split = columnValue.split(",");

        //4.从拆分结果数组中获取Address需要的具体数据
        String city = split[0];
        String province = split[1];
        String street = split[2];

        Address address = new Address(city, province, street);
        return address;
    }
}
