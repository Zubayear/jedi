package com.zubayear.dsaj.concurrency;

public class ProducerConsumer {
    private static int[] buffer;
    private static int count;

    private static final Object lock = new Object();

    static class Producer {
        void produce() throws InterruptedException {
            synchronized (lock) {
                if (isFull(buffer)) {
                    lock.wait();
                }
                buffer[count++] = 1;
                lock.notify(); // notify the thread that is waiting
            }
        }
    }

    private static boolean isFull(int[] buffer) {
        return buffer.length == count;
    }

    static class Consumer {
        void consume() throws InterruptedException {
            synchronized (lock) {
                if (isEmpty()) {
                    lock.wait(); // park the thread / release the lock
                }
                buffer[--count] = 0;
                lock.notify();
            }
        }
    }

    private static boolean isEmpty() {
        return count == 0;
    }

    public static void main(String[] args) throws InterruptedException {
        buffer = new int[10];
        count = 0;
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        Runnable produceTask = () -> {
            for (int i = 0; i < 50; i++) {
                try {
                    producer.produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Done Producing");
        };
        Runnable consumeTask = () -> {
            for (int i = 0; i < 50; i++) {
                try {
                    consumer.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Done Consuming");
        };

        Thread consumerThread = new Thread(consumeTask);
        Thread producerThread = new Thread(produceTask);

        consumerThread.start();
        producerThread.start();

        System.out.println("Waiting");
        consumerThread.join();
        producerThread.join();

        System.out.println("Data in buffer: " + count);
    }
}
