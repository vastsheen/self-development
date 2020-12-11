package com.xh.interfaces;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public interface AutowiredTest {
    void printName();


    class TestObjA implements AutowiredTest {

        @Override
        public void printName() {
            System.out.println(this.getClass().getName());
        }
    }

    class TestObjB implements AutowiredTest {

        @Override
        public void printName() {
            System.out.println(this.getClass().getName());
        }
    }
}

