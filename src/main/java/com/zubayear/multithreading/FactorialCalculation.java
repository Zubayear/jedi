package com.zubayear.multithreading;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FactorialCalculation {
    public static void main(String[] args) throws InterruptedException {
        List<Long> numbers = Arrays.asList(10000000L, 1L, 1234L, 56789L, 101911L, 23455666L);
        List<FactorialThread> factorialThreads = new ArrayList<>();
        for (long n : numbers) factorialThreads.add(new FactorialThread(n));
        for (Thread ft : factorialThreads) {
//            ft.setDaemon(true);
            ft.start();
        }

        for (Thread ft : factorialThreads) ft.join(2000);


        for (int i = 0; i < numbers.size(); ++i) {
            FactorialThread ft = factorialThreads.get(i);
            if (ft.isFinished()) System.out.println("Factorial of " + numbers.get(i) + " = " + ft.getResult());
            else System.out.println("Still calculating...");
        }


    }

    static class FactorialThread extends Thread {
        private final long input;
        private BigInteger result = BigInteger.ZERO;
        private boolean finished = false;

        public FactorialThread(long input) {
            this.input = input;
        }

        @Override
        public void run() {
            result = factorial(input);
            finished = true;
        }

        BigInteger factorial(long n) {
            BigInteger tmp = BigInteger.ONE;
            for (long i = n; i > 0; --i) tmp = tmp.multiply(new BigInteger(Long.toString(i)));
            return tmp;
        }

        boolean isFinished() {
            return finished;
        }

        BigInteger getResult() {
            return result;
        }
    }
}
