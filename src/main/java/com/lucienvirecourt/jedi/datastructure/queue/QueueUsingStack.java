package com.lucienvirecourt.jedi.datastructure.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class QueueUsingStack<T> {
  private final Deque<T> input;
  private final Deque<T> output;

  public QueueUsingStack() {
    this.input = new ArrayDeque<>();
    this.output = new ArrayDeque<>();
  }

  public void enqueue(T x) {
    input.offer(x);
  }

  public T dequeue() {
    if (!output.isEmpty()) return output.poll();
    while (!input.isEmpty()) output.offer(input.poll());
    assert output.peek() != null;
    return output.poll();
  }

  public T peek() {
    if (!output.isEmpty()) return output.peek();
    while (!input.isEmpty()) output.offer(input.poll());
    assert output.peek() != null;
    return output.peek();
  }

  public boolean empty() {
    return input.isEmpty() && output.isEmpty();
  }
}
