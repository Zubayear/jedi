package com.zubayear.dsaj.multithreading;

public class Experiment {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new NewThread();
        t.start();
        Thread.sleep(1000);
    }

    static class NewThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello from " + this.getName());
        }
    }
}
