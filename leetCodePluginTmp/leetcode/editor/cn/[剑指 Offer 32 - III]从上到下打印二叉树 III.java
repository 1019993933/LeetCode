//请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。 
//
// 
//
// 例如: 
//给定二叉树: [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层次遍历结果： 
//
// [
//  [3],
//  [20,9],
//  [15,7]
//]
// 
//
// 
//
// 提示： 
//
// 
// 节点总数 <= 1000 
// 
// Related Topics 树 广度优先搜索 
// 👍 55 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Deque;

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root==null) return result;

        //由于打印需求，需要用两个队列来存储遍历中间节点，第二个队列用来存储下一行的
        Deque<TreeNode> curQ = new LinkedList<>();
        Deque<TreeNode> nextQ = new LinkedList<>();

        curQ.add(root);
        int lineCount = 0;
        while(!curQ.isEmpty())
        {
            List<Integer> lineData = new ArrayList<>();

            //处理当前行，将所有子节点保存到第二个队列
            while(!curQ.isEmpty()) {
                TreeNode curNode = curQ.poll();
                if (curNode.left != null) nextQ.add(curNode.left);
                if (curNode.right != null) nextQ.add(curNode.right);
                if (lineCount%2==0){
                    lineData.add(curNode.val);
                }
                else {
                    lineData.add(0, curNode.val);
                }
            }

            ++lineCount;
            result.add(lineData);

            //处理完当前行，交换第一个和第二队队列，处理下一行
            if (!nextQ.isEmpty())
            {
                Deque<TreeNode> tmp = curQ;
                curQ = nextQ;
                nextQ = tmp;
            }
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
