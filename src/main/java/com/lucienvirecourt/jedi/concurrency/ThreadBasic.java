package com.lucienvirecourt.jedi.concurrency;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadBasic extends Thread {
  @Override
  public void run() {
    System.out.println(Thread.currentThread().getState());
    System.out.println("Thread running: " + Thread.currentThread());
  }

  public static void main(String[] args) {
    ThreadBasic tb = new ThreadBasic();
    System.out.println("Thread state: " + tb.getState());
    tb.start();
  }
}

class CallableBasic implements Callable<String> {

  @Override
  public String call() {
    System.out.println("call running on: " + Thread.currentThread());
    return "Result from callable";
  }

  public static void main(String[] args) {
    System.out.println("main running on: " + Thread.currentThread());
    Future<String> future;
    try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
      future = executorService.submit(new CallableBasic());
      System.out.println("Result: " + future.get(200, TimeUnit.MILLISECONDS));
    } catch (ExecutionException | InterruptedException | TimeoutException e) {
      System.out.println(e.getMessage());
    }
  }
}

class LifecycleDemo {
  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(() -> {
      try {
        System.out.printf("[Thread: %s] State in run: [%s]\n", Thread.currentThread().getName(), Thread.currentThread().getState());
        Thread.sleep(2000); // TIMED_WAITING
        System.out.printf("[Thread: %s] After sleep: [%s]\n", Thread.currentThread().getName(), Thread.currentThread().getState());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    // 1. NEW
    System.out.printf("[Thread: %s] State after creation: [%s]\n", thread.getName(), thread.getState());

    // 2. RUNNABLE (soon becomes RUNNING)
    thread.start();
    Thread.sleep(100); // small delay to let thread enter sleep
    System.out.printf("[Thread: %s] State after start: [%s]\n", thread.getName(), thread.getState());

    // 3. TIMED_WAITING (thread should be sleeping)
    Thread.sleep(500); // thread still sleeping
    System.out.printf("[Thread: %s] State during sleep: [%s]\n", thread.getName(), thread.getState());

    // 4. Wait for thread to finish (TERMINATED)
    thread.join();
    System.out.printf("[Thread: %s] Final state after join: [%s]\n", thread.getName(), thread.getState());
  }
}

class BlockedStateDemo {
  public static void main(String[] args) throws InterruptedException {
    Object lock = new Object();
    Thread t1 = new Thread(() -> {
      synchronized (lock) {
        try {
          System.out.printf("[Thread: %s] acquired lock. [%s]\n", Thread.currentThread().getName(), Thread.currentThread().getState());
          lock.wait();
          System.out.printf("[Thread: %s] state. [%s]\n", Thread.currentThread().getName(), Thread.currentThread().getState());
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        System.out.printf("[Thread: %s] releasing lock. [%s]\n", Thread.currentThread().getName(), Thread.currentThread().getState());
      }
    });

    Thread t2 = new Thread(() -> {
      synchronized (lock) {
        System.out.printf("[Thread: %s] acquired lock. [%s]\n", Thread.currentThread().getName(), Thread.currentThread().getState());
        System.out.printf("[Thread: %s] notifying...\n", Thread.currentThread().getName());
        lock.notify();
      }
    });

    t1.start();
    Thread.sleep(100);
    t2.start();
    Thread.sleep(100);

    System.out.printf("[Thread: %s] state [%s]\n", t1.getName(), t1.getState());
    System.out.printf("[Thread: %s] state [%s]\n", t2.getName(), t2.getState());

    t1.join();
    t2.join();
  }
}

class ProducerConsumer {
  private static final int CAPACITY = 5;
  private static final Deque<String> data = new ArrayDeque<>();
  static final Object lock = new Object();

  public static void main(String[] args) throws InterruptedException {
    Thread producer = new Thread(() -> {
      synchronized (lock) {
        while (true) {
          while (data.size() == CAPACITY) {
            try {
              System.out.println("Producer waiting");
              lock.wait(3000);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          long now = System.currentTimeMillis();
          data.offer(now + "");
          lock.notify(); // notify the consumer after producing as it might be waiting
        }
      }
    });

    Thread consumer = new Thread(() -> {
      while (true) {
        synchronized (lock) {
          while (data.isEmpty()) {
            try {
              System.out.println("Consumer waiting");
              lock.wait(3000);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          data.poll();
          lock.notify(); // notify the producer after consuming as it might be waiting
        }
      }
    });

    producer.start();
    consumer.start();

    producer.join();
    consumer.join();
  }
}

class Counter {
  private int count = 0;
  private final ReentrantLock lock = new ReentrantLock();

  public void increment() {
    lock.lock();
    System.out.printf("[%s]: [Thread: %s] lock acquire\n", LocalDateTime.now(), Thread.currentThread().getName());
    try {
      inc();
    } finally {
      lock.unlock();
    }
  }

  private void inc() {
    lock.lock();
    System.out.printf("[%s]: [Thread: %s] lock acquire\n", LocalDateTime.now(), Thread.currentThread().getName());
    try {
      count++;
    } finally {
      lock.unlock();
    }
  }

  public int getCount() {
    return count;
  }
}

class Account {
  private int balance;
  private final ReentrantLock lock = new ReentrantLock();

  public Account(int balance) {
    this.balance = balance;
  }

  public boolean withdraw(int amount) {
    if (balance < amount) return false;
    balance -= amount;
    return true;
  }

  public void deposit(int amount) {
    balance += amount;
  }

  public ReentrantLock getLock() {
    return lock;
  }

  public int getBalance() {
    return balance;
  }
}

class TransferTask implements Runnable {
  private final Account from;
  private final Account to;
  private final int amount;

  public TransferTask(Account from, Account to, int amount) {
    this.from = from;
    this.to = to;
    this.amount = amount;
  }

  @Override
  public void run() {
    try {
      if (from.getLock().tryLock(1, TimeUnit.SECONDS)) {
        try {
          if (to.getLock().tryLock(1, TimeUnit.SECONDS)) {
            try {
              if (from.withdraw(amount)) {
                to.deposit(amount);
                System.out.printf("[%s]: [Thread: %s] transferred amount %d\n", LocalDateTime.now(), Thread.currentThread(), amount);
              } else {
                System.out.printf("[%s]: [Thread: %s] not enough funds. \n", LocalDateTime.now(), Thread.currentThread());
              }
            } finally {
              to.getLock().unlock();
            }
          } else {
            System.out.printf("[%s]: [Thread: %s] failed to lock within 1s\n", LocalDateTime.now(), Thread.currentThread());
          }
        } finally {
          from.getLock().unlock();
        }
      } else {
        System.out.printf("[%s]: [Thread: %s] failed to lock within 1s\n", LocalDateTime.now(), Thread.currentThread());
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

class BankTransferDemo {
  public static void main(String[] args) throws InterruptedException {
    var acc1 = new Account(1000);
    var acc2 = new Account(1000);

    Thread t1 = new Thread(new TransferTask(
      acc1, acc2, 100
    ), "T1");

    Thread t2 = new Thread(new TransferTask(
      acc2, acc1, 300
    ), "T2");

    t1.start();
    t2.start();
    t1.join();
    t2.join();
  }
}
