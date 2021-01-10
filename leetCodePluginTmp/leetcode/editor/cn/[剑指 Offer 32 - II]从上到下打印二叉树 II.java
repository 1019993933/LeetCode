// 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
//
//
//
// 例如:
// 给定二叉树: [3,9,20,null,null,15,7],
//
// 3
// / \
// 9 20
// / \
// 15 7
//
//
// 返回其层次遍历结果：
//
// [
// [3],
// [9,20],
// [15,7]
// ]
//
//
//
//
// 提示：
//
//
// 节点总数 <= 1000
//
//
// 注意：本题与主站 102 题相同：https://leetcode-cn.com/problems/binary-tree-level-order-tra
// versal/
// Related Topics 树 广度优先搜索
// 👍 64 👎 0

// leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode right; TreeNode(int x) {
 * val = x; } }
 */
import java.util.*;

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root==null) return result;

        //由于打印需求，需要用两个队列来存储遍历中间节点，第二个队列用来存储下一行的
        Queue<TreeNode> curQ = new LinkedList<>();
        Queue<TreeNode> nextQ = new LinkedList<>();

        curQ.add(root);
        while(!curQ.isEmpty())
        {
            List<Integer> line = new ArrayList<>();

            //处理当前行，将所有子节点保存到第二个队列
            while(!curQ.isEmpty()) {
                TreeNode curNode = curQ.poll();
                if (curNode.left != null) nextQ.add(curNode.left);
                if (curNode.right != null) nextQ.add(curNode.right);
                line.add(curNode.val);
            }
            result.add(line);

            //处理完当前行，交换第一个和第二队队列，处理下一行
            if (!nextQ.isEmpty())
            {
                Queue<TreeNode> tmp = curQ;
                curQ = nextQ;
                nextQ = tmp;
            }
        }

        return result;
    }
}
// leetcode submit region end(Prohibit modification and deletion)
