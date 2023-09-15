package com.zubayear.customds.heap;

public class MaxHeap {
    private final int[] data;
    public int cap, size;

    public MaxHeap(int cap) {
        this.cap = cap;
        data = new int[cap+1]; // start at 1
        data[0] = 0;
    }

    public void add(int val) throws Exception {
        size++; // since we need to start at 1
        if (size > cap) {
            size--;
            throw new Exception("added too many elements!");
        }
        data[size] = val;
        int idx = size, parent = idx/2;
        // inserted value > parent then swap
        while (idx > 1 && data[idx] > data[parent]) {
            swap(data, idx, parent);
            idx = parent;
            parent = idx/2;
        }
    }

    public int peek() {
        return data[1];
    }

    private void swap(int[] array, int i, int j) {
        if (i == j) {
            return; // No need to swap if indices are the same
        }

        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }

    public int pop() {
        // remove root
        // copy the last element and place at root
        // compare children of new root & swap root with greater child
        int elem = data[1];
        data[1] = data[size]; // put last element at root
        data[size] = elem; //// adding the deleted element at last place
        int i = 1, j = 2*i, n = size;
        while (j < n-1) {
            // compare left and right child and find max one
            if (data[j+1] > data[j]) {
                j = j+1;
            }
            // compare root with child
            if (data[i] < data[j]) {
                swap(data, i, j);
                i = j;
                j = 2*i;
            } else {
                break;
            }
        }
        size--;
        return elem;
    }

    public void print() {
        System.out.print("Heap: ");
        for (int i = 1; i < data.length; ++i) System.out.print(data[i] + " ");
        System.out.println();
    }

    public void sort() {
        // delete from heap and as we get that last spot free push the deleted element there
        for (int i = 0; i < cap; ++i) {
            pop();
        }
    }


}
