package com.framework.thread.producerconsumer.wait;

import android.util.Log;

/**
 * author:vipkid
 * time :2019/3/11 3:11 PM
 *
 * 生产者和消费者的缓冲区
 *
 *
 * 1. 生产方法和消费方法全部写在缓冲区这个类里面，只需求修改这个类就可以了，其他地方不需要修改，不影响原有的框架设计
 * 2. 生产者和消费者在判断临界条件时，需要用while关键字；因为如果满足临界条件会阻塞在wait方法那里，在下次竞争锁的
 *    时候，如果竞争成功会从wait那里继续执行，if语句会导致错过判断条件，while会重复判断
 * 3. 支持多个消费者和多个生产者同时进行
 * 4. 每个生产者和消费者都是无限循环操作
 *
 */
class Storage {
    private final static String TAG = "Storage";
    private final Object Lock = new Object();

    private static int EMPTY = 0;
    private static int FULL = 10;
    private static int value = 0;


    void produce(String name) {
        synchronized (Lock) {
            try {
                Log.d(TAG, "Producer-生产者" + name + "获取锁");
                while (Storage.value == Storage.FULL) {
                    Log.d(TAG, "Producer-仓库已经满了！！！" + name + "等待");
                    Lock.wait();
                    Log.d(TAG, "Producer-生产者" + name + "被唤醒");
                }

                Log.d(TAG, "Producer-生产者" + name + "正在生产商品。。。");
                Thread.sleep(100);
                Storage.value++;
                Log.d(TAG, "Producer-生产者" + name + "生产完成，当前缓存池里有" + Storage.value + "个商品！");
                Lock.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    void consume(String name) {
        synchronized (Lock) {
            try {
                Log.d(TAG, "Consumer-消费者" + name + "获取锁");
                while (Storage.value == Storage.EMPTY) {
                    Log.d(TAG, "Consumer-仓库已经空了！！！" + name + "等待");
                    Lock.wait();
                    Log.d(TAG, "Consumer-消费者" + name + "被唤醒");
                }
                Log.d(TAG, "Consumer-消费者" + name + "正在消费商品。。。");
                Thread.sleep(100);
                Storage.value--;
                Log.d(TAG, "Consumer-消费者" + name + "消费完成，当前缓存池里有" + Storage.value + "个商品！");
                Lock.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
