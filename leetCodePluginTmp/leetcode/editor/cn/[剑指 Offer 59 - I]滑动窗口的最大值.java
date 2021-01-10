// 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
//
// 示例:
//
// 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
// 输出: [3,3,5,5,6,7]
// 解释:
//
// 滑动窗口的位置 最大值
// --------------- -----
// [1 3 -1] -3 5 3 6 7 3
// 1 [3 -1 -3] 5 3 6 7 3
// 1 3 [-1 -3 5] 3 6 7 5
// 1 3 -1 [-3 5 3] 6 7 5
// 1 3 -1 -3 [5 3 6] 7 6
// 1 3 -1 -3 5 [3 6 7] 7
//
//
//
// 提示：
//
// 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
//
// 注意：本题与主站 239 题相同：https://leetcode-cn.com/problems/sliding-window-maximum/
// Related Topics 队列 Sliding Window
// 👍 148 👎 0

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

// leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0 || k == 0) return new int[0];

        int[] res = new int[nums.length - k + 1];

        Deque<Integer> queue = new LinkedList<>();
        int i = 1 - k; //初始化i的位置 j - i + 1 == k {j=0, i=1-k}

        for (int j = 0; j < nums.length; j++, i++) {
            //当前滑动窗口去掉的最大值在第一位，则需要移除
            if (i>0 && queue.peekFirst()==nums[i-1])
            {
                queue.removeFirst();
            }

            //遍历queue,保证递减顺序关系，nums[j]>queue.peekLast()只有大于才移除，保证了重复数据，即相等也删除的话，会丢失重复数据
            while(!queue.isEmpty() && nums[j]>queue.peekLast())
            {
                queue.removeLast();
            }
            queue.addLast(nums[j]);

            if (i>=0)
            {
                res[i] = queue.peekFirst();
            }
        }

        return res;
    }
}
// leetcode submit region end(Prohibit modification and deletion)
