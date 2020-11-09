package com.xh.demo.aop;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ProxyTarget implements ApplicationContextAware {

    @Getter
    private ApplicationContext context;

    public void say(){
        System.out.println(context);
        System.out.println("self: hello");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
