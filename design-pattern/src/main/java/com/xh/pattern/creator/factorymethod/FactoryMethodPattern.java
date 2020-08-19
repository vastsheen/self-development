package com.xh.pattern.creator.factorymethod;

import com.xh.pattern.model.Factory;
import com.xh.pattern.model.Product;
import com.xh.pattern.model.ProductA;

public class FactoryMethodPattern implements Factory {
    @Override
    public Product factoryMethod() {
        return new ProductA();
    }

    public static void main(String[] args) {
        Factory factory;
        factory = new FactoryMethodPattern(); //可通过配置文件实现
        Product product= factory.factoryMethod();
        product.saySomething();
    }
}
