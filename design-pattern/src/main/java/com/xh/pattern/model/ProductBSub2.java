package com.xh.pattern.model;

public class ProductBSub2 extends ProductB {
    @Override
    public void saySomething() {
        System.out.print("sub2 ");
        super.saySomething();
    }
}
