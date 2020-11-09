package com.xuhao.thread;


import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class CountDownLatchDemo {
    //定义计数器
    static  final int  SIZE=20;

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(SIZE);
        Random random = new Random();
        ExecutorService executorService = Executors.newCachedThreadPool();
        //让等待所有子线程执行完毕
        Controller controller = new Controller(latch);
        executorService.execute(controller);

        //将SIZE个小任务去执行，多个子线程任务
        for(int i=0;i<SIZE;i++){
            executorService.execute(new Module(latch,"模块"+(i+1),random.nextInt(2000)));
        }
        executorService.shutdown();//并不是终止线程的运行，而是禁止在这个Executor中添加新的任务
    }
}


class Module implements Runnable{

    private CountDownLatch latch;

    private String name;

    private int random;

    public Module(CountDownLatch latch,String name,int random){
        this.latch=latch;
        this.name=name;
        this.random=random;
    }

    @Override
    public void run() {
        work();
        latch.countDown(); //当前线程调用此方法，则计数减一

    }

    private void work() {
        try {
            TimeUnit.MILLISECONDS.sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " 完成，耗时:" + random);
    }
}


class Controller implements Runnable {

    private CountDownLatch latch;

    public Controller(CountDownLatch latch) {
        super();
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();  //调用此方法会一直阻塞当前线程，直到计时器的值为0
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有任务都完成，任务完成");
    }
}