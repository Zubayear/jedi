package com.lucienvirecourt.jedi.datastructure.queue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CircularQueue<T> implements Iterable<T> {

  private final List<T> data;
  private int front = 0;
  private int rear = 0;
  private int size = 0;
  private final int cap;

  public CircularQueue(int cap) {
    if (cap <= 0) throw new IllegalArgumentException("Capacity must be greater than 0");
    this.cap = cap;
    data = new ArrayList<>(cap);
    for (int i = 0; i < cap; i++) data.add(null);
  }

  public int size() {
    return size;
  }

  private boolean isFull() {
    return size >= cap;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void enqueue(T element) {
    if (isFull()) throw new IllegalStateException("Queue is full");
    data.set(rear % cap, element);
    rear++;
    size++;
  }

  public T dequeue() {
    if (isEmpty()) throw new RuntimeException("Queue is empty");
    int idx = front % cap;
    T item = data.get(idx);
    front++;
    size--;
    data.set(idx, null);
    return item;
  }

  public T peek() {
    if (isEmpty()) throw new RuntimeException("Queue is empty");
    return data.get(front % cap);
  }

  public void clear() {
    rear = 0;
    front = 0;
    size = 0;
    for (int i = 0; i < cap; ++i) data.set(i, null);
  }

  public String queueStr() {
    if (isEmpty()) throw new RuntimeException("Queue is empty");
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = front; i <= rear - 1; i++) {
      sb.append(data.get(i % cap)).append(",");
    }
    sb.deleteCharAt(sb.length() - 1);
    sb.append("]");
    return sb.toString();
  }

  private class CircularQueueIterator implements Iterator<T> {
    private int count = 0;
    private int index = front;

    @Override
    public boolean hasNext() {
      return count < size;
    }

    @Override
    public T next() {
      if (!hasNext()) throw new NoSuchElementException();
      T item = data.get(index % cap);
      index++;
      count++;
      return item;
    }
  }

  @Override
  public Iterator<T> iterator() {
    return new CircularQueueIterator();
  }
}
