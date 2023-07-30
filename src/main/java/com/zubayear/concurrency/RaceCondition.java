package com.zubayear.concurrency;

import com.sun.net.httpserver.HttpServer;

import java.util.concurrent.*;

public class RaceCondition {
    public static void main(String[] args) throws InterruptedException {
        IntWrapper intWrapper = new IntWrapper(0);
        Runnable runnable = () -> {
            for (int i = 0; i < 10_000; ++i) {
                intWrapper.incrementValue();
            }
        };

        // 100 thread
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(runnable);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("value: " + intWrapper.getValue());
    }
}
