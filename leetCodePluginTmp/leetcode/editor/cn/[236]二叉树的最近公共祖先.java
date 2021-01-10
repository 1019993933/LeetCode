//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。 
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4] 
//
// 
//
// 
//
// 示例 1: 
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出: 3
//解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
// 
//
// 示例 2: 
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出: 5
//解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
// 
//
// 
//
// 说明: 
//
// 
// 所有节点的值都是唯一的。 
// p、q 为不同节点且均存在于给定的二叉树中。 
// 
// Related Topics 树 
// 👍 892 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    //进度深度遍历，用两个队列记录p, q的路径，对应的最后一个公共节点即为最近的公共祖先
    Deque<TreeNode> q1 = new LinkedList<>();
    Deque<TreeNode> q2 = new LinkedList<>();
    boolean findQ = false;
    boolean findP = false;

    Deque<TreeNode> queue = new LinkedList<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.left == q || p.right == q) {
            return p;
        }
        if (q.left == p || q.right == p) {
            return q;
        }

        if (p == q) {
            return p;
        }

        return dfsLowestCommonAncestor(root, p, q);
    }

    private TreeNode dfsLowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);

        while (!q1.isEmpty() && !q2.isEmpty()) {
            TreeNode n1 = q1.pollFirst();
            TreeNode n2 = q2.pollFirst();
            if (q1.peekFirst() != q2.peekFirst() || q1.isEmpty()) {
                return n1;
            }
            if (q2.isEmpty()) {
                return n2;
            }
        }

        return null;
    }

    private void dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return;
        }

        if (root.left == root.right && root != p && root != q) {
            return;
        }
        queue.add(root);

        if (root == p) {
            findP = true;
            q1.addAll(queue);
        }

        if (root == q) {
            findQ = true;
            q2.addAll(queue);
        }

        if (!findP || !findQ) {
            dfs(root.left, p, q);
        }

        if (!findP || !findQ) {
            dfs(root.right, p, q);
        }

        queue.pollLast();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
