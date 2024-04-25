package com.zubayear.dsaj.customds.queue;

import java.util.Arrays;

public class SimpleQueue {
    private final int[] data;
    private int count;
    private final int size;
    private int front;
    private int rear;

    public SimpleQueue(int size) {
        this.size = size;
        data = new int[size];
        front = rear = count = 0;
    }


    public boolean enqueue(int x) {
        if (count == size) {
            return false;
        }
        data[rear % size] = x;
        rear++;
        count++;
        return true;
    }

    public int dequeue() {
        if (count == 0) {
            return -1;
        }
        int idx = front % size;
        int value = data[idx];
        data[idx] = -1;
        front++;
        count--;
        return value;
    }

    public int peek() {
        return data[front % size];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = front; i <= rear - 1; i++) {
            sb.append(data[i % size]).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        System.out.println(sb);
    }
}
