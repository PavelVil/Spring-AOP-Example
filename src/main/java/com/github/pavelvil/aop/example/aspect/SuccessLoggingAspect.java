package com.github.pavelvil.aop.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class SuccessLoggingAspect {

    private static final Logger LOG = LoggerFactory.getLogger(SuccessLoggingAspect.class);

    @AfterReturning("within(com.github.pavelvil.aop.example.service.*) && " +
            "@within(com.github.pavelvil.aop.example.annotation.SuccessLogging)")
    public void successLogging(JoinPoint joinPoint) {
        LOG.info("Метод успешно выполнился: {}", joinPoint.getSignature().toLongString());
        LOG.info("--------------------");
    }

}
