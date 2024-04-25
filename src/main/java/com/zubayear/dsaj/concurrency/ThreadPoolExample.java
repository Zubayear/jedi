package com.zubayear.dsaj.concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10_000_000; i++) {
            executor.submit(() -> System.out.println("Using " + Thread.currentThread().getName()));
        }
        executor.shutdown();
    }
}
