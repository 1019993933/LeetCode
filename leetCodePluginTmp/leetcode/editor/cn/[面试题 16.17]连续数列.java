//给定一个整数数组，找出总和最大的连续数列，并返回总和。 
//
// 示例： 
//
// 输入： [-2,1,-3,4,-1,2,1,-5,4]
//输出： 6
//解释： 连续子数组 [4,-1,2,1] 的和最大，为 6。
// 
//
// 进阶： 
//
// 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。 
// Related Topics 数组 分治算法 动态规划 
// 👍 57 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxSubArray(int[] nums) {
        //状态方程， fi为第i个前面的最大值，则有 fi = max(f(i-1)， f(i-1)+x)
        int pre = 0;
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            pre = Math.max(nums[i], pre+nums[i]);
            max = Math.max(max, pre);
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
