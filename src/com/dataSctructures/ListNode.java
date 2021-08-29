package com.dataSctructures;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() { }

    public ListNode(int val) { 
        this.val = val; 
    }

    public ListNode(int val, ListNode next) { 
        this.val = val; this.next = next; 
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
