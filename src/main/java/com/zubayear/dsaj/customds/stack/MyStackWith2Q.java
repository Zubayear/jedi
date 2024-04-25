package com.zubayear.dsaj.customds.stack;

import java.util.Deque;
import java.util.LinkedList;

public class MyStackWith2Q {
    private Deque<Integer> q1, q2;

    public MyStackWith2Q() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    public void push(int x) {
        // add x to q2
        // q1 -> q2 (element by element)
        // swap (q1 & q2) or poll from q2 and push to q1
        q2.offer(x);
        while (!q1.isEmpty()) {
            q2.offer(q1.poll());
        }
        while (!q2.isEmpty()) {
            q1.offer(q2.poll());
        }
    }

    public int pop() {
        // remove the top of q1
        return q1.poll();
    }

    public int top() {
        return q1.peek();
    }

    public boolean empty() {
        return q1.isEmpty();
    }

}
