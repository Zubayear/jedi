package com.zubayear.dsaj.customds.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class MyStackWith1Q {
    private Deque<Integer> q;

    public MyStackWith1Q() {
        q = new ArrayDeque<>();
    }

    public void push(int x) {
        // push x
        // loop through qSize-1 and push all values again
        q.offer(x);
        int size = q.size();
        for (int i = 0; i < size - 1; ++i) {
            q.offer(q.poll());
        }
    }

    public int pop() {
        Integer value = q.poll();
        if (value == null) {
            value = -1;
        }
        return value;
    }

    public int top() {
        Integer value = q.peek();
        if (value == null) {
            value = -1;
        }
        return value;
    }

    public boolean empty() {
        return q.isEmpty();
    }

}
