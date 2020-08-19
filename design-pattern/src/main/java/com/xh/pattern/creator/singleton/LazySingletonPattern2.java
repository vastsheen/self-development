package com.xh.pattern.creator.singleton;

/**
 * 缩小sync范围，需要使用双重判断
 */
public class LazySingletonPattern2 {
    private static LazySingletonPattern2 instance = null;

    private LazySingletonPattern2() {
    }

    public LazySingletonPattern2 getInstance() {
        if (instance == null) {
            synchronized (LazySingletonPattern2.class) {
                if (instance == null) {
                    instance = new LazySingletonPattern2();
                }
            }
        }
        return instance;
    }
}
