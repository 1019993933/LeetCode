//给你一个链表数组，每个链表都已经按升序排列。 
//
// 请你将所有链表合并到一个升序链表中，返回合并后的链表。 
//
// 
//
// 示例 1： 
//
// 输入：lists = [[1,4,5],[1,3,4],[2,6]]
//输出：[1,1,2,3,4,4,5,6]
//解释：链表数组如下：
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//将它们合并到一个有序链表中得到。
//1->1->2->3->4->4->5->6
// 
//
// 示例 2： 
//
// 输入：lists = []
//输出：[]
// 
//
// 示例 3： 
//
// 输入：lists = [[]]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// k == lists.length 
// 0 <= k <= 10^4 
// 0 <= lists[i].length <= 500 
// -10^4 <= lists[i][j] <= 10^4 
// lists[i] 按 升序 排列 
// lists[i].length 的总和不超过 10^4 
// 
// Related Topics 堆 链表 分治算法 
// 👍 991 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null || lists.length==0){
            return null;
        }

        if (lists.length==1)
        {
            return lists[0];
        }

        if (lists.length==2)
        {
            return mergeTwoList(lists[0], lists[1]);
        }

        int i = 0, j=lists.length-1;
        ArrayList<ListNode> tmpResult = new ArrayList<>();
        while (i<=j)
        {
            tmpResult.add(mergeTwoList(lists[i], lists[j]));
            ++i;
            --j;
        }

        return mergeKLists(tmpResult.toArray(new ListNode[0]));
    }

    private ListNode mergeTwoList(ListNode l1, ListNode l2)
    {
        if (l1==null){
            return l2;
        }

        if (l2==null){
            return l1;
        }

        if (l1==l2)
        {
            return l1;
        }

        ListNode head = new ListNode();
        ListNode tail=head;

        while(l1!=null && l2!=null){
            if (l1.val<=l2.val)
            {
                tail.next = l1;
                tail = tail.next;
                l1 = l1.next;
            }
            else
            {
                tail.next = l2;
                tail = tail.next;
                l2 = l2.next;
            }
        }

        tail.next = (l1==null)? l2: l1;

        return head.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
