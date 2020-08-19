package com.xh.pattern.creator.abstractfactory;

import com.xh.pattern.model.*;

public class AbstractFactoryPattern {

    class ConcreteFactory1 extends AbstractFactory {
        //工厂方法一
        public ProductA createProductA() {
            return new ProductASub1();
        }

        //工厂方法二
        public ProductB createProductB() {
            return new ProductBSub2();
        }
    }

    class ConcreteFactory2 extends AbstractFactory {
        //工厂方法一
        public ProductA createProductA() {
            return new ProductASub2();
        }

        //工厂方法二
        public ProductB createProductB() {
            return new ProductBSub1();
        }
    }

    public static void main(String[] args) {
        AbstractFactoryPattern pattern = new AbstractFactoryPattern();
        AbstractFactory concreteFactory1 = pattern.new ConcreteFactory1();
        concreteFactory1.createProductA().saySomething();
        concreteFactory1.createProductB().saySomething();

        AbstractFactory concreteFactory2 = pattern.new ConcreteFactory2();
        concreteFactory2.createProductA().saySomething();
        concreteFactory2.createProductB().saySomething();
    }
}
