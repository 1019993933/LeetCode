//给定一个整数数组，判断是否存在重复元素。 
//
// 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。 
//
// 
//
// 示例 1: 
//
// 输入: [1,2,3,1]
//输出: true 
//
// 示例 2: 
//
// 输入: [1,2,3,4]
//输出: false 
//
// 示例 3: 
//
// 输入: [1,1,1,3,3,4,3,2,4,2]
//输出: true 
// Related Topics 数组 哈希表 
// 👍 313 👎 0


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums==null || nums.length==0){
            return false;
        }

        return contansDuplicate(nums, 0, nums.length-1);
    }

    public boolean contansDuplicate(int[] nums, int left, int right){
        if (left==right) {
            return false;
        }

        int mid = (left+right)>>1;
        if (contansDuplicate(nums, left, mid)){
            return true;
        }
        if (contansDuplicate(nums, mid+1, right)){
            return true;
        }

        return merge(nums, left, mid, right);
    }

    private boolean merge(int[] nums, int left, int mid, int right) {
        int i = left;
        int j = mid+1;
        int k = 0;
        int[] data = new int[right-left+1];
        while (i<=mid || j<=right){
            if (i<=mid && j<=right) {
                if (nums[i] == nums[j]) {
                    return true;
                }
                data[k++] = nums[i]<nums[j]? nums[i++]: nums[j++];
            } else if (i<=mid){
                data[k++] = nums[i++];
            } else{
                data[k++] = nums[j++];
            }
        }
        System.arraycopy(data, 0, nums, left, right-left+1);
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
