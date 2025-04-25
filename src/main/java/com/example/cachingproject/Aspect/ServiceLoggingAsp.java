package com.example.cachingproject.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class ServiceLoggingAsp {

    @Before("execution( * com.example.cachingproject.services.impl.EmployeeServiceImpl.*(..))")
    public void anyMethod(JoinPoint joinPoint){
        log.info("method execution start : {}",joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "execution(* com.example.cachingproject.services.impl.EmployeeServiceImpl.getEmployeeById(..))",returning = "empObj")
    public void callGetEmpById(Object empObj,JoinPoint joinPoint){
        log.info("method return {} from {}",empObj,joinPoint.getSignature());
    }




}
