package com.study.leetcode.solutions;

import com.study.utils.ListNode;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LK148 {

    @Test
    public void test()
    {
        ListNode head = null;
        for (int i = 0; i < 5; i++) {
            head = new ListNode(i, head);
        }

        sortList(head);
    }



    public ListNode sortList(ListNode head) {
        ListNode listNode = mergeSort(head);
        return listNode;
    }

    public ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode sendHead = splitList(head);

        head = mergeSort(head);
        sendHead = mergeSort(sendHead);

        return merge(head, sendHead);
    }

    private ListNode splitList(ListNode head){
        ListNode fastNode = head;
        ListNode slowNode = head;
        while(fastNode != null && fastNode.next!=null && fastNode.next.next!=null){
            slowNode = slowNode.next;
            if (fastNode.next!=null) {
                fastNode = fastNode.next.next;
            }
        }

        ListNode result = slowNode.next;
        slowNode.next = null;
        return result;
    }

    public ListNode merge(ListNode h1, ListNode h2) {
        ListNode head = new ListNode(0, null);
        ListNode tail = head;
        while (h1 != null || h2 != null) {
            if (h1 != null && h2 != null) {
                if (h1.val > h2.val) {
                    tail.next = h2;
                    h2 = h2.next;
                } else {
                    tail.next = h1;
                    h1 = h1.next;
                }
            } else if (h1 != null) {
                tail.next = h1;
                h1 = h1.next;
            } else {
                tail.next = h2;
                h2 = h2.next;
            }
            tail = tail.next;
        }

        return head.next;
    }
}
