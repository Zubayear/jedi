package com.zubayear.customds.stack;

import java.util.Deque;
import java.util.LinkedList;

public class MyStackWith1Q {
    private Deque<Integer> q;

    public MyStackWith1Q() {
        q = new LinkedList<>();
    }

    public void push(int x) {
        // push x
        // loop through qSize-1 and push all values again
        q.offer(x);
        for (int i = 0; i < q.size(); ++i) q.offer(q.poll());
    }

    public int pop() {
        return q.poll();
    }

    public int top() {
        return q.peek();
    }

    public boolean empty() {
        return q.isEmpty();
    }

}
