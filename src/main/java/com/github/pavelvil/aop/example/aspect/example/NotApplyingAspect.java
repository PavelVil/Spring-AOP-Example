package com.github.pavelvil.aop.example.aspect.example;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NotApplyingAspect {

    private static final Logger LOG = LoggerFactory.getLogger(NotApplyingAspect.class);

    @Before("execution(* notExistedMethod())")
    public void before() {
        LOG.info("I will not print data");
    }

}
