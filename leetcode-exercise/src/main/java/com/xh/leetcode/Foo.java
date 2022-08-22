package com.xh.leetcode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuhao
 * @date 2022/8/18
 */
class Foo {

    AtomicInteger integer = new AtomicInteger(0);
    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        synchronized (Foo.class) {
            while (integer.get() != 0) {
                Foo.class.wait();
            }
            printFirst.run();
            integer.incrementAndGet();
            Foo.class.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        synchronized (Foo.class) {
            while (integer.get() != 1) {
                Foo.class.wait();
            }
            printSecond.run();
            integer.incrementAndGet();
            Foo.class.notify();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        synchronized (Foo.class) {
            while (integer.get() != 2) {
                Foo.class.wait();
            }
            printThird.run();
            integer.incrementAndGet();
            Foo.class.notify();
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        for (int i = 0; i < 100; i++) {
            extracted();
            System.out.println("__________");
//        }
    }

    private static void extracted() throws InterruptedException {
        final Foo foo = new Foo();
        final Thread second = new Thread(() -> {
            try {
                foo.second(() -> System.out.println("second"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        second.start();


        new Thread(() -> {
            try {
                foo.first(() -> System.out.println("first"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                foo.third(() -> System.out.println("third"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}