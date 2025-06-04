package com.lucienvirecourt.jedi.datastructure.linkedlist;

public class ListNode<T> {
  protected T val;
  protected ListNode<T> next;
  protected ListNode<T> prev;

  public ListNode(T val, ListNode<T> next) {
    this.val = val;
    this.next = next;
  }

  public ListNode(T val) {
    this.val = val;
  }

}
