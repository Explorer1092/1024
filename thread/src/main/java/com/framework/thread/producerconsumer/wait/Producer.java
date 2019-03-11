package com.framework.thread.producerconsumer.wait;

/**
 * author:vipkid
 * time :2019/3/11 3:03 PM
 */
public class Producer implements Runnable {
    Storage storage;
    String name;

    public Producer(Storage storage, String name) {
        this.storage = storage;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            storage.produce(name);
        }
    }
}
