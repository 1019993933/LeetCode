//给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c +
// d 的值与 target 相等？找出所有满足条件且不重复的四元组。 
//
// 注意： 
//
// 答案中不可以包含重复的四元组。 
//
// 示例： 
//
// 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
//
//满足要求的四元组集合为：
//[
//  [-1,  0, 0, 1],
//  [-2, -1, 1, 2],
//  [-2,  0, 0, 2]
//]
// 
// Related Topics 数组 哈希表 双指针 
// 👍 704 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Solution1 solution1 = new Solution1();
        return solution1.fourSum(nums, target);
    }

    public class Solution1{
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        public List<List<Integer>> fourSum(int[] nums, int target) {
            if (nums==null || nums.length<4){
                return ans;
            }

            List<Integer> lst = new ArrayList<>();
            fourSum(0, 4, lst, nums, target);
            return ans;
        }

        public void fourSum(int startIndex, int num, List<Integer> curList, int[] nums, int target) {
            if (nums.length-startIndex<num){
                return;
            }

            if (nums.length-startIndex==num){
                int sum = 0;
                List<Integer> lst = new ArrayList<>();
                for (int i = startIndex; i < nums.length; i++) {
                    lst.add(nums[i]);
                    sum += nums[i];
                }
                if (sum==target){
                    curList.addAll(lst);
                    ans.add(curList);
                }
                return;
            }

            fourSum(startIndex+1, num, curList, nums, target);
            curList.add(nums[startIndex]);
            fourSum(startIndex+1, num-1, curList, nums, target-nums[startIndex]);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
