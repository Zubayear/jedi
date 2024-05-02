package com.zubayear.dsaj.datastructure.stack;

import java.util.ArrayList;
import java.util.List;

public class GenericStack<T> {
    private final List<T> data;
    private int top = -1;

    public GenericStack() {
        data = new ArrayList<>();
    }

    public void push(T val) {
        if (val == null) {
            throw new IllegalArgumentException("Invalid value");
        }
        data.add(++top, val);
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return data.get(top);
    }

    public T peek(int pos) {
        if (pos < 0) {
            throw new IllegalArgumentException("Invalid position");
        }
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return data.get(pos - 1);
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        T val = data.get(top);
        data.remove(top);
        top--;
        return val;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return data.size();
    }

    public void clear() {
        top = -1;
        data.clear();
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
