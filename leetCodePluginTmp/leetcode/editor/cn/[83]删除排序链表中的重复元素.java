//给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。 
//
// 示例 1: 
//
// 输入: 1->1->2
//输出: 1->2
// 
//
// 示例 2: 
//
// 输入: 1->1->2->3->3
//输出: 1->2->3 
// Related Topics 链表 
// 👍 425 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.HashMap;
import java.util.HashSet;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head==null || head.next == null){
            return head;
        }

        ListNode cur=head;
        while(cur.next!=null){
            if (cur.next.val == cur.val){
                cur.next = cur.next.next;
            }
            else{
                cur = cur.next;
            }
        }

        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
