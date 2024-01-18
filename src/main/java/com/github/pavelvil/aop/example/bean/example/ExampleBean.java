package com.github.pavelvil.aop.example.bean.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExampleBean implements IExampleBean {

    private static final Logger LOG = LoggerFactory.getLogger(ExampleBean.class);

    @Override
    public void info() {
        try {
            LOG.info("INFO METHOD");
        } finally {
            LOG.info("INFO METHOD FINALLY");
        }
    }
}
