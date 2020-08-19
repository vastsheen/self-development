package com.xh.pattern.model;

public class ProductASub2 extends ProductA{
    @Override
    public void saySomething() {
        System.out.print("sub2 ");
        super.saySomething();
    }
}
