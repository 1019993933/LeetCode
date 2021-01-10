//给定一个二叉树，原地将它展开为一个单链表。 
//
// 
//
// 例如，给定二叉树 
//
//     1
//   / \
//  2   5
// / \   \
//3   4   6 
//
// 将其展开为： 
//
// 1
// \
//  2
//   \
//    3
//     \
//      4
//       \
//        5
//         \
//          6 
// Related Topics 树 深度优先搜索 
// 👍 674 👎 0


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
    public void flatten(TreeNode root) {
        doflatten(root);
    }

    private TreeNode doflatten(TreeNode root){
        if (root==null || root.left==root.right){
            return root;
        }

        TreeNode left = doflatten(root.left);
        TreeNode right = doflatten(root.right);

        if (left!=null){
            root.right = left;
            getTreeTail(left).right = right;
        }

        return root;
    }

    private TreeNode getTreeTail(TreeNode root){
        if (root==null || root.right==null){
            return null;
        }

        TreeNode curNode = root;
        while(curNode.right!=null){
            curNode = curNode.right;
        }
        return curNode;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
