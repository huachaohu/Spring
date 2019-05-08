package com.huachao.demo.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class TimeAspect {
    @Around("execution(* com.huachao.demo.web.controller.UserController.*(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint point) throws Throwable {
        System.out.println("time aspect start");
        Object[] args = point.getArgs();
        for (Object arg : args){
            System.out.println("arg is : "+arg);
        }
        long start = new Date().getTime();
        Object obj = point.proceed();
        long end = new Date().getTime();

        System.out.println("耗时："+(end-start));
        System.out.println("time aspect end");

        return obj;
    }
}
