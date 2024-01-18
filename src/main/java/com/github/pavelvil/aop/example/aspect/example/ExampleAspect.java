package com.github.pavelvil.aop.example.aspect.example;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExampleAspect {

    private static final Logger LOG = LoggerFactory.getLogger(ExampleAspect.class);

    @Pointcut("execution(public void info())")
    public void beforePointcut() {}

    @Pointcut("execution(public void info())")
    public void afterPointcut() {}

    @Before("beforePointcut()")
    public void beforeMethod() {
        LOG.info("BEFORE METHOD INFO");
    }

    @After("afterPointcut()")
    public void afterMethod() {
        LOG.info("AFTER METHOD INFO");
    }

}
