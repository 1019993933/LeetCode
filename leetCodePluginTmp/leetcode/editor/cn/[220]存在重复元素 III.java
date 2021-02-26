//在整数数组 nums 中，是否存在两个下标 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值小于等于 t ，且满足 i 和 j 的差的
//绝对值也小于等于 ķ 。 
//
// 如果存在则返回 true，不存在返回 false。 
//
// 
//
// 示例 1: 
//
// 输入: nums = [1,2,3,1], k = 3, t = 0
//输出: true 
//
// 示例 2: 
//
// 输入: nums = [1,0,1,1], k = 1, t = 2
//输出: true 
//
// 示例 3: 
//
// 输入: nums = [1,5,9,1,5,9], k = 2, t = 3
//输出: false 
// Related Topics 排序 Ordered Map 
// 👍 285 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums==null || nums.length<=1){
            return false;
        }

        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            Integer s = set.ceiling(key);
            if (s!=null && checkDistance(s, key, t)){
                return true;
            }
            s = set.floor(key);
            if (s!=null && checkDistance(key, s, t)){
                return true;
            }

            set.add(key);
            if (set.size()>k){
                set.remove(nums[i-k]);
            }
        }
        return false;
    }

    boolean checkDistance(int larger, int small, int t) {
        if (larger >= Integer.MIN_VALUE + t && larger<=Integer.MAX_VALUE-t) {
            return small >= larger - t && small <= larger +t;
        } else if (larger > Integer.MAX_VALUE - t){
            return small>= larger-t;
        } else {
            return small <= larger+t;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
