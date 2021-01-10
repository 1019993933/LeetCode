//输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。 
//
// 
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
//
// 
//
// 提示： 
//
// 
// 节点总数 <= 10000 
// 
//
// 注意：本题与主站 113 题相同：https://leetcode-cn.com/problems/path-sum-ii/ 
// Related Topics 树 深度优先搜索 
// 👍 108 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.*;

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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        return recur(root, sum);

    }


    public List<List<Integer>> recur(TreeNode root, int sum) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        ArrayList<Integer> ls = new ArrayList<>();

        if (root==null){
            return result;
        }

        if (root.left==null && root.right==null)
        {
            if (sum==root.val) {
                ls.add(sum);
                result.add(ls);
            }
            return result;
        }


        if (root.left!=null)
        {
            List<List<Integer>> left = recur(root.left, sum-root.val);
            if (left.size()>0){
                for (List<Integer> lst:
                     left) {
                    lst.add(0, root.val);
                }
                result.addAll(left);
            }

        }

        if (root.right!=null)
        {
            List<List<Integer>> right = recur(root.right, sum-root.val);
            if (right.size()>0){
                for (List<Integer> lst:
                        right) {
                    lst.add(0, root.val);
                }
                result.addAll(right);
            }
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
