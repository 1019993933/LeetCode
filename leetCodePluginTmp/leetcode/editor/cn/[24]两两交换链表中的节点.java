//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4]
//输出：[2,1,4,3]
// 
//
// 示例 2： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 100] 内 
// 0 <= Node.val <= 100 
// 
// Related Topics 链表 
// 👍 749 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
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
    public ListNode swapPairs(ListNode head) {
        if (head==null || head.next==null){
            return head;
        }

        int k=0;
        ListNode tmp = head;
        ListNode newHead = new ListNode();
        ListNode tail = newHead;
        while(tmp!=null){
            if (k%2==0)
            {
                tail.next = tmp.next;
                tmp.next = tmp.next.next;
                tail = tail.next;
            }

            ++k;
            tmp = tmp.next;
        }

        tmp = null;
        ListNode l1 = head, l2 = newHead.next;
        ListNode result = new ListNode();
        tail = result;
        while(l1!=null && l2!=null)
        {
            tail.next = l2;
            l2 = l2.next;
            tail.next.next = l1;
            l1 = l1.next;
            tail = tail.next.next;
        }

        tail.next = (l1==null)? l2: l1;

        return result.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
