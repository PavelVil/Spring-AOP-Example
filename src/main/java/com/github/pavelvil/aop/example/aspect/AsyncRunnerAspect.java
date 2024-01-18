package com.github.pavelvil.aop.example.aspect;

import com.github.pavelvil.aop.example.exception.ApplicationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Aspect
@Order(4)
public class AsyncRunnerAspect {

    private static final Logger LOG = LoggerFactory.getLogger(AsyncRunnerAspect.class);

    @Pointcut("execution(@com.github.pavelvil.aop.example.annotation.Asynchronously public void add*(..) " +
            "throws com.github.pavelvil.aop.example.exception.ApplicationException)")
    public void asyncRunPointcut() {}

    @Around("asyncRunPointcut()")
    public Object asyncRunner(ProceedingJoinPoint joinPoint) {
        return CompletableFuture.runAsync(() -> {
            try {
                LOG.info("Асинхронный запуск в AsyncRunnerAspect");
                joinPoint.proceed();
            } catch (Throwable e) {
                throw new ApplicationException(e);
            }
        });
    }

}
