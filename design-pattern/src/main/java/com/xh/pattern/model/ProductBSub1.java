package com.xh.pattern.model;

public class ProductBSub1 extends ProductB {
    @Override
    public void saySomething() {
        System.out.print("sub1 ");
        super.saySomething();
    }
}
