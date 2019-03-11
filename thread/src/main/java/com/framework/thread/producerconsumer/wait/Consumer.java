package com.framework.thread.producerconsumer.wait;

/**
 * author:vipkid
 * time :2019/3/11 3:02 PM
 */
public class Consumer implements Runnable {
    Storage storage;
    String name;

    public Consumer(Storage storage, String name) {
        this.storage = storage;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            storage.consume(name);
        }
    }
}
