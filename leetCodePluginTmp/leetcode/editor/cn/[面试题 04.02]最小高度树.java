//给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。示例: 给定有序数组: [-10,-3,0,5,9], 一个可能
//的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：           0          / \        -3 
//  9        /   /      -10  5 Related Topics 树 深度优先搜索 
// 👍 66 👎 0


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
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums==null || nums.length==0){
            return null;
        }

        return sortedArrayToBST(nums, 0, nums.length-1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int left, int right){
        if (nums==null || nums.length==0){
            return null;
        }

        //从二分原则，只要初始值给的正确不会出现left>right的情况
        if (left==right){
            return new TreeNode(nums[left]);
        }
        else if (left<right){
            int mid = (left+right)>>1;
            TreeNode root = new TreeNode(nums[mid]);
            root.left = sortedArrayToBST(nums, left, mid-1);
            root.right = sortedArrayToBST(nums, mid+1, right);
            return root;
        }
        else{
            return null;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
