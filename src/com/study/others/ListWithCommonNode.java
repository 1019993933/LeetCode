package com.study.others;

import com.study.utils.ListNode;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * 求两个有公共节点的链表，找出该节点
 */
public class ListWithCommonNode {
    private ListNode h1 = new ListNode();
    private ListNode h2 = new ListNode();

    public void generateData()
    {
        ListNode tail = h1;
        ListNode common = new ListNode(1000);
        tail = common;
        for (int i = 1; i < 5; i++) {
            tail.next = new ListNode(1000 + i);
            tail = tail.next;
        }

        tail = h1;
        for (int i = 0; i < 2; i++) {
            tail.next = new ListNode(100+i);
            tail = tail.next;
        }
        tail.next = common;

        tail = h2;
        for (int i = 0; i < 1; i++) {
            tail.next = new ListNode(10+i);
            tail = tail.next;
        }
        tail.next = common;
    }

    public ListNode findCommon(ListNode h1, ListNode h2){
        ListNode tmp1 = h1;
        ListNode tmp2 = h2;
        while(tmp1!=tmp2){
            tmp1 = (tmp1==null? h2: tmp1.next);
            tmp2 = (tmp2==null? h1: tmp2.next);
        }

            System.out.println(tmp1==null? "不相交": "相交");

        return tmp1;
    }

    @Test
    public void doTest()
    {
        generateData();

        ListNode common = findCommon(h1, h2);
        h1 = h1;
        h2 = h2;
    }




}
