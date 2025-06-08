package com.lucienvirecourt.jedi.datastructure.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackUsingQueue<T> {
  private final Deque<T> deque;

  public StackUsingQueue() {
    this.deque = new ArrayDeque<>();
  }

  public void push(T item) {
    deque.offer(item);
    int size = deque.size();
    int i = 1;
    while (i < size) {
      deque.offer(deque.poll());
      i++;
    }
  }

  public T pop() {
    return deque.poll();
  }

  public T peek() {
    return deque.peek();
  }
}
