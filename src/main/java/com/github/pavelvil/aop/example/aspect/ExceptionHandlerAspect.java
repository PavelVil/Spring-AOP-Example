package com.github.pavelvil.aop.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class ExceptionHandlerAspect {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerAspect.class);

    @AfterThrowing(pointcut = "within(com.github.pavelvil.aop.example.service.*) && " +
            "execution(* * (..) throws @com.github.pavelvil.aop.example.annotation.Throw *)", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        LOG.info("Произошла ошибка при вызове метода: {}", joinPoint.getSignature().toShortString());
        LOG.info("Ошибку: {}", ex.getMessage());
    }

}
