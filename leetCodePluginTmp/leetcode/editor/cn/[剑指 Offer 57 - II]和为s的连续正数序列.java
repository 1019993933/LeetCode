import java.util.ArrayList;
import java.util.List;

//输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
//
// 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。 
//
// 
//
// 示例 1： 
//
// 输入：target = 9
//输出：[[2,3,4],[4,5]]
// 
//
// 示例 2： 
//
// 输入：target = 15
//输出：[[1,2,3,4,5],[4,5,6],[7,8]]
// 
//
// 
//
// 限制： 
//
// 
// 1 <= target <= 10^5 
// 
//
// 
// 👍 181 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] findContinuousSequence(int target) {
        int i = 1;
        int j = 1;
        int sum = 0;
        List<int []> res = new ArrayList<>();

        while(i<=target/2)
        {
            if (sum<target){
                sum += j;
                j++;
            }
            else if (sum>target)
            {
                sum -= i;
                i++;
            }
            else {
                int[] item = new int[j - i];
                for (int k = i; k < j; k++) {
                    item[k - i] = k;
                }
                res.add(item);

                sum -= i;
                ++i;
            }
        }

        return res.toArray(new int[res.size()][]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
