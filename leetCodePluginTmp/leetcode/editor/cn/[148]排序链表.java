// 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
//
// 进阶：
//
//
// 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
//
//
//
//
// 示例 1：
//
//
// 输入：head = [4,2,1,3]
// 输出：[1,2,3,4]
//
//
// 示例 2：
//
//
// 输入：head = [-1,5,3,4,0]
// 输出：[-1,0,3,4,5]
//
//
// 示例 3：
//
//
// 输入：head = []
// 输出：[]
//
//
//
//
// 提示：
//
//
// 链表中节点的数目在范围 [0, 5 * 104] 内
// -105 <= Node.val <= 105
//
// Related Topics 排序 链表
// 👍 919 👎 0

// leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode next; ListNode() {} ListNode(int val) {
 * this.val = val; } ListNode(int val, ListNode next) { this.val = val; this.next = next; } }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        return mergeSortDownToUp(head);
    }

    // 自顶向下的递归
    public ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode sendHead = splitList(head);

        head = mergeSort(head);
        sendHead = mergeSort(sendHead);

        return merge(head, sendHead);
    }

    private ListNode splitList(ListNode head) {
        ListNode fastNode = head;
        ListNode slowNode = head;
        while (fastNode != null && fastNode.next != null && fastNode.next.next != null) {
            slowNode = slowNode.next;
            if (fastNode.next != null) {
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

    private int lenthList(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    // 自底向上的递归方法，有点意思
    public ListNode mergeSortDownToUp(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ///自底向上的递归的意思是采用步长Grap进行分组，将整个链表分为size/grap组，每个grap组内是有序的，每一轮做的事情是将两个
        //相邻的grap组进行合并，该合并的含义为对两个已经有序的链表进行合并，采用传统的合并算法即可
        // 初始grap=0,即对第单个元素，本身即为有序
        // GRAP==1，即对相邻的两个节点进行排序，最后分成size/2组有序链表
        // grap=2, 对两个包含2个有序链表进行合并，得到4个为一组的有序链表
        // 循环上述步骤，直至grap>=len/2
        //======================  |11111| |222222| *****************************
        //head               pre  h1      h2       next
        int len = lenthList(head);
        ListNode newHead = new ListNode(-1, head);

        for (int grap = 1; grap < len; grap<<=1) {

            //每一轮grap重新合并，都是从head开始的，此轮循环里面 grap*i~grap*(i+1)-1都是有序的
            //grap = 2： 合并单个元素构造成有序区间[0, 1][2,3][4,5]...[len-2, len-1]/[len] /最后一组可能为一个
            //grap = 2:  合并上一个有序区间构造成新的有序区间[0, 3][4，]
            ListNode curNode = newHead.next;
            ListNode preNode = newHead;

            //while循环即是针对第一个分组，与下一个分组进行合并
            while(curNode!=null)
            {
                //取得第一组
                ListNode h1 = curNode;
                for (int i = 1; i < grap && curNode.next!=null; i++) {
                    curNode = curNode.next;
                }
                //保存第二组head
                ListNode h2 = curNode.next;
                curNode.next = null; //置h1结束

                //开始计算第二组链表(可能没有第二组，所以需要判断h2是否为空
                curNode = h2;
                for (int i = 1; i < grap && curNode!=null && curNode.next!=null; i++) {
                    curNode = curNode.next;
                }

                ListNode next = null;
                if (curNode!=null)
                {
                    //缓存下一轮待计算的节点
                    next = curNode.next;
                    curNode.next = null; //将第二组的结尾置为NULL
                    curNode = next;
                }

                preNode.next = merge(h1, h2);

                //得到新的计算尾部
                while(preNode.next!=null)
                {
                    preNode = preNode.next;
                }
            }
        }

        return newHead.next;
    }

}
// leetcode submit region end(Prohibit modification and deletion)
