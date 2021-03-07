package com.msz.product.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@EnableAspectJAutoProxy
@Component
@Slf4j
@Aspect
public class ControllerAspect {

    @Around("execution(* com.msz.product.controller..*(..))")
    public Object aroundControllerMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.nanoTime();
        Object proceed = proceedingJoinPoint.proceed();
        log.info("::Controller method time execution::" + (System.nanoTime() - start) + "ns");
        return proceed;
    }

}
