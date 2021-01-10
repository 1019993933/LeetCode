// 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
//
// 假设一个二叉搜索树具有如下特征：
//
//
// 节点的左子树只包含小于当前节点的数。
// 节点的右子树只包含大于当前节点的数。
// 所有左子树和右子树自身必须也是二叉搜索树。
//
//
// 示例 1:
//
// 输入:
// 2
// / \
// 1 3
// 输出: true
//
//
// 示例 2:
//
// 输入:
// 5
// / \
// 1 4
//   / \
//   3 6
// 输出: false
// 解释: 输入为: [5,1,4,null,null,3,6]。
//   根节点的值为 5 ，但是其右子节点值为 4 。
//
// Related Topics 树 深度优先搜索 递归
// 👍 877 👎 0

// leetcode submit region begin(Prohibit modification and deletion)

import java.util.Deque;
import java.util.Stack;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode right; TreeNode() {}
 * TreeNode(int val) { this.val = val; } TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left =
 * left; this.right = right; } }
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        // return Solution1.isValidBST(root);
        return Solution2.isValidBST(root);
    }

    /**
     * 递归实现
     */
    static class Solution1 {
        public static boolean isValidBST(TreeNode root) {
            return isValidBST(root, Long.MAX_VALUE, Long.MIN_VALUE);
        }

        public static boolean isValidBST(TreeNode root, long max, long min) {
            if (root == null || root.left == root.right) {
                return true;
            }

            if (root.left != null && root.left.val >= root.val) {
                return false;
            }

            if (root.right != null && root.right.val <= root.val) {
                return false;
            }

            if (!checkValidValue(root.left, max, min) || !checkValidValue(root.right, max, min)) {
                return false;
            }

            return isValidBST(root.left, (long)root.val, min) && isValidBST(root.right, max, (long)root.val);
        }

        private static boolean checkValidValue(TreeNode node, long max, long min) {
            return node == null || (node.val < max && node.val > min);
        }

    }

    static class Solution2 {
        public static boolean isValidBST(TreeNode curNode) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            Long preNodeValue = Long.MIN_VALUE;

            //如果栈不为空，或者root不为空
            while(!stack.isEmpty() || curNode!=null){
                //中序遍历，先把左节点全入栈
                while(curNode!=null){
                    stack.push(curNode);
                    curNode = curNode.left;
                }

                //出栈，得到第一个元素,此元素必然是当前最左的一个节点
                curNode = stack.pop();
                
                //preNodeValue初始化为最小值，第一次此条件必然不成立，后续即赋值为上一次遍历的节点值
                if(curNode.val<=preNodeValue){ 
                    return false;
                }
                inOrder = (long)curNode.val;

                curNode = curNode.right;
            }

            return true;
        }
    }

}
// leetcode submit region end(Prohibit modification and deletion)
