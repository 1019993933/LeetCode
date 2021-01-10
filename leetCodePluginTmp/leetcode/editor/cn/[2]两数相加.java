//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。 
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。 
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 示例： 
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8
//原因：342 + 465 = 807
// 
// Related Topics 链表 数学 
// 👍 5240 👎 0


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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return solution1(l1, l2);
    }

    private ListNode solution1(ListNode l1, ListNode l2) {
        if (l1 ==null) return l2;
        if (l2 ==null) return l1;
        //上面两个判断保证了两个链表非空，即都有第一个节点

        ListNode tmp1 = l1;
        ListNode tmp2 = l2;
        int sum = tmp1.val + tmp2.val;

        int inc = sum / 10;
        tmp1.val = sum % 10;

        //由于需要对尾部节点进行保存，所以要从next开始遍历，上面要针对第一个节点先作一遍操作
        // 计算结果都保存在 l1链表
        while (tmp1.next!=null && tmp2.next!=null)
        {
            sum = tmp1.next.val + tmp2.next.val + inc;
            tmp1.next.val = sum % 10;
            inc = sum / 10;

            tmp1 = tmp1.next;
            tmp2 = tmp2.next;
        }

        //循环结束是一条链表走到了结尾，因为之前结果存在l1，如果是l1先到结尾，则把l2剩余的先链过来
        if (tmp1.next == null)
        {
            tmp1.next = tmp2.next;
            tmp2 = tmp2.next;
        }


        //然后只需要针对tmp1后面部分进行计算
        while( tmp1.next != null && inc>0)
        {
            sum = tmp1.next.val + inc;
            tmp1.next.val = sum % 10;
            inc = sum / 10;
            tmp1 = tmp1.next;
        }

        //结束后，如果inc大于0，则需要新增加一个节点
        if (inc>0)
        {
            tmp1.next = new ListNode(inc);
        }

        return l1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
