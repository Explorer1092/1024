package com.framework.thread.producerconsumer.wait;

/**
 * author:vipkid
 * time :2019/3/11 3:01 PM
 */
public class Test {

    public void test() {
        Storage storage = new Storage();
        new Thread(new Consumer(storage, "c1")).start();
        new Thread(new Producer(storage, "p1")).start();
        new Thread(new Producer(storage, "p2")).start();
        new Thread(new Producer(storage, "p3")).start();
        new Thread(new Producer(storage, "p4")).start();
        new Thread(new Producer(storage, "p5")).start();
    }

}
