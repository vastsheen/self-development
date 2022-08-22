package com.xh.leetcode;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @author xuhao
 * @date 2022/8/18
 */
class ZeroEvenOdd {
    private int n;

    private int m = 0;
    private Semaphore zeroSema = new Semaphore(1);

    private Semaphore evenSema = new Semaphore(0);

    private Semaphore oddSema = new Semaphore(0);


//    public ZeroEvenOdd(int n) {
//        this.n = n;
//    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while (m < n) {
            zeroSema.acquire();
            printNumber.accept(0);
            m++;
            if (m % 2 == 0) {
                evenSema.release();
            } else {
                oddSema.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while (m <= n) {
            evenSema.acquire();
            printNumber.accept(m);
            zeroSema.release();
            if (m == n || m == n - 1) {
                break;
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (m <= n) {
            oddSema.acquire();
            printNumber.accept(m);
            zeroSema.release();
            if (m == n|| m == n - 1) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        final ZeroEvenOdd foo = new ZeroEvenOdd(5);
//        final Thread second = new Thread(() -> {
//            try {
//                foo.odd1(System.out::print);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//        });
//        second.start();
//
//
//        new Thread(() -> {
//            try {
//                foo.zero1(System.out::print);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//        }).start();
//
//        new Thread(() -> {
//            try {
//                foo.even1(System.out::print);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }).start();
        final Thread second = new Thread(() -> {
            try {
                foo.odd(System.out::print);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        second.start();


        new Thread(() -> {
            try {
                foo.zero(System.out::print);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }).start();

        new Thread(() -> {
            try {
                foo.even(System.out::print);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }


    Semaphore[] semaphores = new Semaphore[3];

    public ZeroEvenOdd(int n) {
        this.n = n;
        semaphores[0] = new Semaphore(1);
        semaphores[1] = new Semaphore(0);
        semaphores[2] = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero1(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            semaphores[0].acquire();
            printNumber.accept(0);
            if (i % 2 == 0) semaphores[1].release();
            else semaphores[2].release();
        }
    }

    public void even1(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i = i + 2) {
            semaphores[1].acquire();
            printNumber.accept(i);
            semaphores[0].release();
        }
    }

    public void odd1(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i = i + 2) {
            semaphores[2].acquire();
            printNumber.accept(i);
            semaphores[0].release();
        }
    }
}