//给定一个二叉树，判断它是否是高度平衡的二叉树。 
//
// 本题中，一棵高度平衡二叉树定义为： 
//
// 
// 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：root = [1,2,2,3,3,null,null,4,4]
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：root = []
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 树中的节点数在范围 [0, 5000] 内 
// -104 <= Node.val <= 104 
// 
// Related Topics 树 深度优先搜索 递归 
// 👍 550 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
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
    public boolean isBalanced(TreeNode root) {
        if (root==null){
            return true;
        }

        boolean isChildBalanced = isBalanced(root.left) && isBalanced(root.right);

        if (isChildBalanced){
         return (Math.abs(depthTree(root.left)-depthTree(root.right))<=1);
        }
        return isChildBalanced;

        //先看子树是否平衡，再判断自身，这样可以减少计算次数
        return isBalanced(root.left) && isBalanced(root.right) && (Math.abs(depthTree(root.left)-depthTree(root.right))<=1);
    }

    private int depthTree(TreeNode node){
        if (node==null){
            return 0;
        }
        if (node.left==node.right){
            return 1;
        }

        int depth =  1 + Math.max(depthTree(node.left), depthTree(node.right));
        //System.out.println(String.format("curNode: %3d, depth: %3d", node.val, depth));
        return depth;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
