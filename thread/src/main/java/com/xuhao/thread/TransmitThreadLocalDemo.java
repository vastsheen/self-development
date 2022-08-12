package com.xuhao.thread;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author xuhao
 * @date 2022/5/13
 */
public class TransmitThreadLocalDemo {

    public static final ThreadLocal<Object>  TL= new TransmittableThreadLocal<>();

    public static ExecutorService ttl;

    public static final AtomicBoolean atomicBoolean = new AtomicBoolean(true);

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        System.out.println(executorService.getClass().getName());

        ttl = TtlExecutors.getTtlExecutorService(executorService);

        System.out.println(ttl.getClass().getName());

        executorService.submit(new TtlRunnableDemo(executorService));

//        ttl.shutdown();
    }



    static class TtlRunnableDemo implements Runnable {

        private final ExecutorService es;

        TtlRunnableDemo(ExecutorService es) {
            this.es = es;
        }

        @Override
        public void run() {
            if (null == TL.get()) {
                System.out.println("set值");
                TL.set("123");
            }
            System.out.println(TL.get() + " current thread id:" + Thread.currentThread().getId());
            if (atomicBoolean.get()) {
                atomicBoolean.compareAndSet(true, false);
                es.submit(new TtlRunnableDemo(null));
            }
        }
    }
}
