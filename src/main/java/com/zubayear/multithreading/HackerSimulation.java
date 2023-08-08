package com.zubayear.multithreading;

import java.util.List;
import java.util.Random;

public class HackerSimulation {
    private static final String ALPHANUMERIC_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random random = new Random();

    public static void main(String[] args) {
        Vault vault = new Vault("700000hack");
        List<Thread> threads = List.of(new RandomHacker(vault), new SystematicHacker(vault), new Police());
        threads.forEach(Thread::start);
    }

    private static class Vault {
        private final String password;

        public Vault(String password) {
            this.password = password;
        }

        boolean matched(String guess) {
            return guess.equals(password);
        }
    }

    private static abstract class HackerThread extends Thread {
        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public void start() {
            System.out.println("Starting Thread " + this.getName());
            super.start();
        }
    }

    private static class RandomHacker extends HackerThread {

        public RandomHacker(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int i = 10; i < 100; ++i) {
                String guess = generateRandomAlphanumeric(i);
                if (vault.matched(guess)) {
                    System.out.println(this.getClass().getSimpleName() + " guessed correctly.");
                    System.exit(0);
                }
            }
        }
    }

    private static class SystematicHacker extends HackerThread {

        public SystematicHacker(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10_000_000; ++i) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                sb.append(i).append("hack");
                if (vault.matched(sb.toString())) {
                    System.out.println(this.getClass().getSimpleName() + " guessed correctly");
                    System.exit(0);
                }
                sb.delete(0, sb.length());
            }
        }
    }

    private static class Police extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10_000; ++i) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(i);
            }
            System.out.println("Game over...");
            System.exit(0);
        }
    }

    public static String generateRandomAlphanumeric(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(ALPHANUMERIC_CHARS.length());
            char randomChar = ALPHANUMERIC_CHARS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
