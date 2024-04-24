package com.zubayear.customds.stack;

import java.util.ArrayList;
import java.util.List;

public class GenericStack<T> {
    private final List<T> data;
    private int top = -1;
    private final int size;

    public GenericStack(int size) {
        this.size = size;
        data = new ArrayList<>(size);
    }

    public boolean push(T val) {
        if (isFull()) {
            throw new RuntimeException("Stack is full");
        }
        data.add(++top, val);
        return true;
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return data.get(top);
    }

    public T peek(int pos) {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        if (top - pos + 1 < 0) {
            throw new RuntimeException("Invalid position");
        }
        return data.get(top - pos + 1);
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return data.get(top--);
    }

    private boolean isFull() {
        return top == size - 1;
    }

    private boolean isEmpty() {
        return top == -1;
    }
}
