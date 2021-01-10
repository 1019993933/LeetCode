// 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
//
// 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
//
// 示例:
//
// 给定的有序链表： [-10, -3, 0, 5, 9],
//
// 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
//
// 0
// / \
// -3 9
// / /
// -10 5
//
// Related Topics 深度优先搜索 链表
// 👍 440 👎 0

// leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode next; ListNode() {} ListNode(int val) {
 * this.val = val; } ListNode(int val, ListNode next) { this.val = val; this.next = next; } }
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode right; TreeNode() {}
 * TreeNode(int val) { this.val = val; } TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left =
 * left; this.right = right; } }
 */
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        Map<Integer, ListNode> map = new HashMap<>();
        TreeNode root = null;
        ListNode curNode = head;
        int index = 0;
        while (curNode != null) {
            map.put(index, curNode);
            ++index;
            curNode = curNode.next;
        }

        return getMidNode(map, 0, map.size()-1);
    }

    private TreeNode getMidNode(Map<Integer, ListNode> map, int start, int end) {
        if (start <= end) {
            int mid = (start+end)>>1;
            TreeNode treeNode = new TreeNode(map.get(mid).val);

            if (start <= end) {
                treeNode.left = getMidNode(map, start, mid - 1);
                treeNode.right = getMidNode(map, mid + 1, end);
            }

            return treeNode;
        }
        return null;

    }
}
// leetcode submit region end(Prohibit modification and deletion)
