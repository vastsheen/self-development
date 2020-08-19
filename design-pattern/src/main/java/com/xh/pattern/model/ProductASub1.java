package com.xh.pattern.model;

public class ProductASub1 extends ProductA {
    @Override
    public void saySomething() {
        System.out.print("sub1 ");
        super.saySomething();
    }
}
