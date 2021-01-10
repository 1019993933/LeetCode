//给你一棵二叉搜索树，请你返回一棵 平衡后 的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。 
//
// 如果一棵二叉搜索树中，每个节点的两棵子树高度差不超过 1 ，我们就称这棵二叉搜索树是 平衡的 。 
//
// 如果有多种构造方法，请你返回任意一种。 
//
// 
//
// 示例： 
//
// 
//
// 输入：root = [1,null,2,null,3,null,4,null,null]
//输出：[2,1,3,null,null,null,4]
//解释：这不是唯一的正确答案，[3,1,4,null,2,null,null] 也是一个可行的构造方案。
// 
//
// 
//
// 提示： 
//
// 
// 树节点的数目在 1 到 10^4 之间。 
// 树节点的值互不相同，且在 1 到 10^5 之间。 
// 
// Related Topics 二叉搜索树 
// 👍 48 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
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
    ArrayList<Integer> lst = new ArrayList<>();

    public TreeNode balanceBST(TreeNode root) {
        inOrder(root);
        return build(0, lst.size()-1);
    }

    private void inOrder(TreeNode node){
        if(node != null){
            inOrder(node.left);
            lst.add(node.val);
            inOrder(node.right);
        }
    }

    private TreeNode build(int p, int r){
        int mid = (p+r)>>1;
        TreeNode node = new TreeNode(lst.get(mid));
        if (p<=mid-1){
            node.left = build(p, mid-1);
        }
        if (r>=mid+1){
            node.right = build(mid+1, r);
        }
        return node;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
