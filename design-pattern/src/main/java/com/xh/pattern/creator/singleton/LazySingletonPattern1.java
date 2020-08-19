package com.xh.pattern.creator.singleton;

public class LazySingletonPattern1 {
    private static LazySingletonPattern1 instance = null;

    private LazySingletonPattern1() {
    }

    synchronized public LazySingletonPattern1 getInstance() {
        if (instance == null) {
            instance = new LazySingletonPattern1();
        }
        return instance;
    }
}
