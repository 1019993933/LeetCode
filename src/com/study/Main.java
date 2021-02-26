package com.study;

import com.study.designmodel.Singleton.Singleton;
import com.study.utils.ListNode;
import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Singleton.getInstance().test();

        boolean [] a = new boolean[]{};
        Boolean [] b = new Boolean[]{};
        System.out.println(a.getClass());
        System.out.println(b.getClass());

        new Thread(()->System.out.println("fda")).start();

        ListNode node1 = new ListNode(1);
        System.out.print( ClassLayout.parseInstance(node1).toPrintable());

        ListNode node2 = new ListNode(2);
        System.out.print( ClassLayout.parseInstance(node2).toPrintable());

        ListNode node3 = new ListNode(2);
        synchronized (node3) {
            System.out.print(ClassLayout.parseInstance(node3).toPrintable());
        }

        System.out.print(ClassLayout.parseInstance(new int[3]).toPrintable());


    }

    @Test
    public void test()
    {
        List<Integer> lst = new ArrayList<>();
        lst.add(10);
        Integer remove = lst.remove(0);

        ListNode head = null;
        for (int i = 0; i < 5; i++) {
            head = new ListNode(i, head);
        }

        splitList(head);
    }

    private ListNode splitList(ListNode head){
        ListNode fastNode = head;
        ListNode slowNode = head;
        while(fastNode.next!=null){
            slowNode = slowNode.next;
            if (fastNode.next.next!=null) {
                fastNode = fastNode.next.next;
            }
        }

        ListNode result = slowNode.next;
        slowNode.next = null;
        return result;
    }



    @Test
    public void testLK19()
    {
        ListNode head = ListNode.getListNode(new int[]{1, 2});
        removeNthFromEnd(head, 2);
    }

    //LK19
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head==null){
            return head;
        }

        if (head.next==null && n==1){
            return null;
        }


        ListNode p = head;
        ListNode newhead = new ListNode(-1, head);
        ListNode q = newhead;
        while (n>0 && p!=null){
            p = p.next;
            --n;
        }

        while(p!=null)
        {
            p = p.next;
            q = q.next;
        }

         if (q.next!=null){
            q.next = q.next.next;
        }

        return newhead;
    }

    @Test
    public void doTest()
    {
        List<Integer> lst = new ArrayList<>();
        lst.forEach(System.out::println);
    }
}
