//给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。 
//
// 你可以假设数组中无重复元素。 
//
// 示例 1: 
//
// 输入: [1,3,5,6], 5
//输出: 2
// 
//
// 示例 2: 
//
// 输入: [1,3,5,6], 2
//输出: 1
// 
//
// 示例 3: 
//
// 输入: [1,3,5,6], 7
//输出: 4
// 
//
// 示例 4: 
//
// 输入: [1,3,5,6], 0
//输出: 0
// 
// Related Topics 数组 二分查找 
// 👍 772 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int searchInsert(int[] nums, int target) {
        return searchInsert(nums, 0, nums.length-1, target);
    }

    public int searchInsert(int[] nums, int p, int q, int target){
        if (p>=q){
            return nums[p]<target? p+1: p;
        }
        else{
            int mid = (p+q)>>1;
            if (nums[mid]==target){
                return mid;
            }
            else if (nums[mid]<target)
            {
                return searchInsert(nums, mid+1, q, target);
            }
            else
            {
                return searchInsert(nums, p, mid-1, target);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
