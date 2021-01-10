// 根据一棵树的前序遍历与中序遍历构造二叉树。
//
// 注意:
// 你可以假设树中没有重复的元素。
//
// 例如，给出
//
// 前序遍历 preorder = [3,9,20,15,7]
// 中序遍历 inorder = [9,3,15,20,7]
//
// 返回如下的二叉树：
//
// 3
// / \
// 9 20
// / \
// 15 7
// Related Topics 树 深度优先搜索 数组
// 👍 811 👎 0

// leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode right; TreeNode(int x) {
 * val = x; } }
 */
class Solution {
    // 中序与前序，后序任一个即可唯一确定一根二叉树， 前序与后序无法确定
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Solution1 s = new Solution1();
        return s.buildTree(preorder, inorder);
    }

    /**
     * 采用递归的方式实现
     */
    class Solution1 {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder == null || preorder.length <= 0) {
                return null;
            }

            return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        }

        private TreeNode buildTree(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
            TreeNode root = new TreeNode(preorder[pStart]);
            if (pStart == pEnd) {
                return root;
            }

            int inOrderInde = findIndex(inorder, preorder[pStart]);
            int leftLen = inOrderInde - iStart;
            int rightLen = iEnd - inOrderInde;

            if (leftLen > 0) {
                TreeNode leftNode =
                    buildTree(preorder, pStart + 1, pStart + leftLen , inorder, iStart, inOrderInde - 1);
                root.left = leftNode;
            }
            if (rightLen > 0) {
                TreeNode rightNode = buildTree(preorder, pStart + leftLen + 1, pEnd, inorder, inOrderInde + 1, iEnd);
                root.right = rightNode;
            }

            return root;
        }

        private int findIndex(int[] data, int val) {
            for (int i = 0; i < data.length; i++) {
                if (data[i] == val) {
                    return i;
                }
            }
            return -1;
        }
    }
    
    class Solution2{
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            Solution1 s = new Solution1();
            return s.buildTree(preorder, inorder);
        }
    }
}
// leetcode submit region end(Prohibit modification and deletion)
