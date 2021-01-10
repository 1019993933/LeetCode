//给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。 
//
// 二叉搜索树的定义如下： 
//
// 
// 任意节点的左子树中的键值都 小于 此节点的键值。 
// 任意节点的右子树中的键值都 大于 此节点的键值。 
// 任意节点的左子树和右子树都是二叉搜索树。 
// 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
//输出：20
//解释：键值为 3 的子树是和最大的二叉搜索树。
// 
//
// 示例 2： 
//
// 
//
// 输入：root = [4,3,null,1,2]
//输出：2
//解释：键值为 2 的单节点子树是和最大的二叉搜索树。
// 
//
// 示例 3： 
//
// 输入：root = [-4,-2,-5]
//输出：0
//解释：所有节点键值都为负数，和最大的二叉搜索树为空。
// 
//
// 示例 4： 
//
// 输入：root = [2,1,3]
//输出：6
// 
//
// 示例 5： 
//
// 输入：root = [5,4,8,3,null,6,3]
//输出：7
// 
//
// 
//
// 提示： 
//
// 
// 每棵树最多有 40000 个节点。 
// 每个节点的键值在 [-4 * 10^4 , 4 * 10^4] 之间。 
// 
// Related Topics 二叉搜索树 动态规划 
// 👍 37 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
import java.util.HashMap;
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
   int maxSum = Integer.MIN_VALUE;

    public int maxSumBST(TreeNode root) {
        getSumBST(root);
        maxSum = maxSum<0? 0: maxSum;
        return maxSum;
    }

    public int getSumBST(TreeNode root) {
        if (root==null){
            return 0;
        }

        if (root.left==null && root.right==null){
            maxSum = Math.max(maxSum, root.val);
            return root.val;
        }

        int leftMax = getSumBST(root.left);
        int rightMax = getSumBST(root.right);


        if (rightMax!=Integer.MIN_VALUE && leftMax!=Integer.MIN_VALUE){
            maxSum = Math.max(rightMax, leftMax);
            if (root.left!=null &&  root.val < root.left.val)
            {
                return Integer.MIN_VALUE;
            }
            if (root.right!=null &&  root.val > root.right.val)
            {
                return Integer.MIN_VALUE;
            }
            int newMax = rightMax+leftMax+root.val;
            maxSum = Math.max(newMax, maxSum);
            return newMax;
        }

        return Integer.MIN_VALUE;
    }

    private void preOrder(TreeNode node){
        if (node!=null){
            map.put(node, Integer.MIN_VALUE);
            preOrder(node.left);
            preOrder(node.right);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
