package com.xh;

import com.xh.demo.aop.ProxyTarget;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Starter {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Starter.class);
        ProxyTarget bean = run.getBean(ProxyTarget.class);
        bean.say();
        ApplicationContext context = bean.getContext();
        System.out.println(context);
    }
}
