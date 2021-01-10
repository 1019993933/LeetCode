//输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
//
// 
//
// 示例 1： 
//
// 输入：head = [1,3,2]
//输出：[2,3,1] 
//
// 
//
// 限制： 
//
// 0 <= 链表长度 <= 10000 
// Related Topics 链表 
// 👍 70 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//三种解题方式，堆栈辅助，递归，反转后遍历
class Solution {
    public int[] reversePrint(ListNode head) {
//        return reversePrint1(head);

//        return solution2(head);

        return solution3(head);
    }

    // 利用堆栈辅助实现
    // 执行耗时:1 ms,击败了72.30% 的Java用户
    // 内存消耗:39.1 MB,击败了82.00% 的Java用户

    // 时间复杂度O(N)
    // 空间复杂度O(N)
    public int[] reversePrint1(ListNode head) {
        if (head==null)
        {
            return new int[]{};
        }

        ListNode tmpNode = head;

        Stack<Integer> stack = new Stack<>();
        while(tmpNode!=null)
        {
            stack.push(tmpNode.val);
            tmpNode = tmpNode.next;
        }

        int[] data = new int[stack.size()];
        int i = 0;
        while(!stack.isEmpty())
        {
            data[i++] = stack.pop();
        }

        return data;
    }

    //采用递归方式解决
    //		执行耗时:2 ms,击败了39.69% 的Java用户
    //		内存消耗:40.2 MB,击败了7.47% 的Java用户
    public int[] solution2(ListNode head) {
        if (head==null)
        {
            return new int[]{};
        }

        ArrayList<Integer> lst = new ArrayList<Integer>();
        doSub(head, lst);

        if (lst.size()==0)
        {
            return new int[]{};
        }

        int data[] = new int[lst.size()];
        for (int i = 0; i < lst.size(); i++) {
            data[i] = lst.get(i);
        }
        return data;
    }

    public void doSub(ListNode head, ArrayList<Integer> lst) {
        if (head==null)
        {
            return;
        }

        doSub(head.next, lst);
        lst.add(head.val);

        return;
    }


//    执行耗时:0 ms,击败了100.00% 的Java用户
//    内存消耗:39 MB,击败了89.04% 的Java用户
    // 由于ListNode本身结构很简单，所以针对其本身的遍历实际性能要比借助辅助集合类要快很多
    public int[] solution3(ListNode head) {
        if (head==null)
        {
            return new int[]{};
        }

        int size = 0;
        ListNode tmpNode = head;
        while (tmpNode!=null)
        {
            ++size;
            tmpNode = tmpNode.next;
        }

        int[] data = new int[size];
        tmpNode = head;
        while (tmpNode!=null)
        {
            data[--size] = tmpNode.val;
            tmpNode = tmpNode.next;
        }

        return data;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


