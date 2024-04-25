package com.zubayear.dsaj.customds.binsearchtree;

public class BinSearchNode<T> {
    public T val;
    public BinSearchNode left, right;

    public BinSearchNode() {
    }

    public BinSearchNode(T val) {
        this.val = val;
    }
}
