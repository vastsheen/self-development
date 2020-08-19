package com.xh.pattern.creator.singleton;

/**
 * 静态内部类机制，加载InnerClassSingleton时不会加载静态内部类，但当调用内部类时，就会开始加载
 * 此为java特性 其他语言不适用
 */
public class InnerClassSingletonPattern {
    private InnerClassSingletonPattern() {
    }

    private static class HolderClass {
        private final static InnerClassSingletonPattern instance = new InnerClassSingletonPattern();
    }

    public static InnerClassSingletonPattern getInstance() {
        return HolderClass.instance;
    }

    public static void main(String args[]) {
        InnerClassSingletonPattern s1, s2;
        s1 = InnerClassSingletonPattern.getInstance();
        s2 = InnerClassSingletonPattern.getInstance();
        System.out.println(s1 == s2);
    }
}
