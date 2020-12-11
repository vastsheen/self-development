package com.xh.demo.config;

import com.xh.interfaces.AutowiredTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutowiredConfig {
    @Bean
    public AutowiredTest buildObjA1() {
        return new AutowiredTest.TestObjA();
    }

    @Bean
    public AutowiredTest buildObjA2() {
        return new AutowiredTest.TestObjA();
    }

    @Bean
    public AutowiredTest buildObjB1() {
        return new AutowiredTest.TestObjB();
    }
}
