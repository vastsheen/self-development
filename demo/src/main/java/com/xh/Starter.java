package com.xh;

import com.google.gson.GsonBuilder;
import com.xh.demo.aop.ProxyTarget;
import com.xh.demo.component.AutowiredTestComponent;
import com.xh.demo.controller.HelloController;
import com.xh.interfaces.AutowiredTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;


@EnableSwagger2
@SpringBootApplication
public class Starter {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext run = SpringApplication.run(Starter.class);
        final AutowiredTestComponent bean = run.getBean(AutowiredTestComponent.class);
        System.out.println(bean.getAutowiredTestList().size());
        bean.getAutowiredTestList().forEach(AutowiredTest::printName);
        System.out.println(new GsonBuilder().create().toJson(bean.getAutowiredTestMap()));
        bean.getAutowiredTestMap().forEach((key, value) -> {
            System.out.print("name :[" + key + "] value:  ");
            value.printName();
        });

//        ProxyTarget bean = run.getBean(ProxyTarget.class);
//        bean.say();
//        HelloController bean1 = run.getBean(HelloController.class);
//        ApplicationContext context = bean.getContext();
//        System.out.println(bean1);
    }
}

