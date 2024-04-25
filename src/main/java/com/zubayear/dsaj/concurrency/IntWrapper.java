package com.zubayear.dsaj.concurrency;

import java.util.TreeMap;

public class IntWrapper {
    private Object key = new Object();

    private int value;

    public IntWrapper(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void incrementValue() {
        synchronized (key) {
            ++value;
        }
    }
}
