package com.zubayear.dsaj.datastructure.queue;

import java.util.ArrayList;
import java.util.List;

public class CircularQueue<T> {

    private final List<T> data;
    private int front = 0, rear = 0;

    public CircularQueue() {
        data = new ArrayList<>();
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void enqueue(T element) {
        data.add(rear % Integer.MAX_VALUE, element);
        rear++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        int idx = front % Integer.MAX_VALUE;
        T item = data.get(idx);
        front++;
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return data.get(front % Integer.MAX_VALUE);
    }

    public void clear() {
        rear = -1;
        front = 0;
        data.clear();
    }

    public String queueStr() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = front; i <= rear - 1; i++) {
            sb.append(data.get(i % Integer.MAX_VALUE)).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }
}
