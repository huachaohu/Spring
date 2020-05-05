package com.huachao.logs.utils.annotation;

import java.lang.annotation.*;

/**
 * 日志注解
 * @author huachao
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface LogInfo {
    String value() default "";
}
