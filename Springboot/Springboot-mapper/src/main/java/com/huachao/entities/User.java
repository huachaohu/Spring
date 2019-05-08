package com.huachao.entities;

import com.huachao.handlers.AddressTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
	CREATE TABLE `table_user` (
		`user_id` INT NOT NULL AUTO_INCREMENT,
		`user_name` VARCHAR (100) NULL,
		`address` VARCHAR (100) NULL,
		`season` VARCHAR (100) NULL,
		PRIMARY KEY (`user_id`)
	);
 * @author Lenovo
 *
 */
@Table(name="table_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	private Integer userId;
	
	private String userName;
	
	//@ColumnType(typeHandler= AddressTypeHandler.class)
	@Column
	private Address address;
	
	//在枚举类型这里无法使用@ColumnType注解注册MyBatis内置的枚举类型处理器
	//@ColumnType(typeHandler=EnumTypeHandler.class)
	@Column
	private SeasonEnum season;
}
