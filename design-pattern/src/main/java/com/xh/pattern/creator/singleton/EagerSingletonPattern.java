package com.xh.pattern.creator.singleton;

public class EagerSingletonPattern {
    private static final EagerSingletonPattern instance = new EagerSingletonPattern();
    private EagerSingletonPattern() { }

    public static EagerSingletonPattern getInstance() {
        return instance;
    }
}
