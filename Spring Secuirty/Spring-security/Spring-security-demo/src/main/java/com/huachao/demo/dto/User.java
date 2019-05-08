package com.huachao.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @ApiModelProperty(value = "用户id")
    private Integer id;
    @NotBlank
    @ApiModelProperty(value = "用户名称")
    private String name;
    private Integer age;
}
