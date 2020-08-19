package com.xh.pattern.creator.simplefactory;

import com.xh.pattern.model.Product;
import com.xh.pattern.model.ProductA;
import com.xh.pattern.model.ProductB;

public class SimpleFactoryPattern {
    public static Product getProduct(String arg) {
        Product product = null;
        if (arg.equalsIgnoreCase("A")) {
            product = new ProductA();
            //初始化设置product
        }
        else if (arg.equalsIgnoreCase("B")) {
            product = new ProductB();
            //初始化设置product
        }
        return product;

    }

    public static void main(String[] args) {
        Product product;
        product = SimpleFactoryPattern.getProduct("A"); //通过工厂类创建产品对象
        product.saySomething();
    }
}
