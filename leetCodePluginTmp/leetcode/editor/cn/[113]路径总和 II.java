//给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//给定如下二叉树，以及目标和 sum = 22， 
//
//               5
//             / \
//            4   8
//           /   / \
//          11  13  4
//         /  \    / \
//        7    2  5   1
// 
//
// 返回: 
//
// [
//   [5,4,11,2],
//   [5,8,4,5]
//]
// 
// Related Topics 树 深度优先搜索 
// 👍 400 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    Stack<Integer> stack = new Stack<>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root==null){
            return ans;
        }

        stack.push(root.val);
        if (root.left==root.right && root.val == sum){
            List<Integer> lst = new ArrayList<>();
            lst.addAll(stack);
            ans.add(lst);
            stack.pop();
            return ans;
        }

        pathSum(root.left, sum-root.val);
        pathSum(root.right, sum-root.val);

        stack.pop();
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
