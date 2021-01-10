//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 
//
// 示例： 
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4
// 
// Related Topics 链表 
// 👍 1372 👎 0


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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1==null)
        {
            return l2;
        }

        if (l2==null)
        {
            return l1;
        }

        ListNode h1 = l1, h2 = l2;
        ListNode head = new ListNode();
        ListNode pre = head;
        while(h1!=null && h2!=null)
        {
            if (h1.val<=h2.val)
            {
                pre.next = h1;
                pre = h1;
                h1 = h1.next;
            }
            else
            {
                pre.next = h2;
                pre = h2;
                h2 = h2.next;
            }
        }

        pre.next = (h1==null)? h2: h1;

        return head.next;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
