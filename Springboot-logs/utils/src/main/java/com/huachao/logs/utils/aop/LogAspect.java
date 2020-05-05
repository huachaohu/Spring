package com.huachao.logs.utils.aop;

import com.huachao.logs.utils.model.LogBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author huachao
 */
@Component
@Aspect
public class LogAspect {
    private final static Logger logger = LoggerFactory.getLogger("kafka");

    @Pointcut(value = "@annotation(com.huachao.logs.utils.annotation.LogInfo)")
    public void log(){}

    @Around(value = "log()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String className = pjp.getTarget().getClass().getSimpleName();
        String methodName = signature.getName();
        LogBean logBean = LogBean.threadLocal.get();
        logBean.setMessage("before "+className+"."+methodName);
        logger.info(logBean.toString());
        //⽅法执⾏
        Object o = pjp.proceed();
        logBean.setMessage("after "+className+"."+methodName);
        logger.info(logBean.toString());
        return o;
    }
}
