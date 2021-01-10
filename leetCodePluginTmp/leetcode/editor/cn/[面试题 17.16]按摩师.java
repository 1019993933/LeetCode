//一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，替按摩
//师找到最优的预约集合（总预约时间最长），返回总的分钟数。 
//
// 注意：本题相对原题稍作改动 
//
// 
//
// 示例 1： 
//
// 输入： [1,2,3,1]
//输出： 4
//解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
// 
//
// 示例 2： 
//
// 输入： [2,7,9,3,1]
//输出： 12
//解释： 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
// 
//
// 示例 3： 
//
// 输入： [2,1,4,5,3,1,1,3]
//输出： 12
//解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。
// 
// Related Topics 动态规划 
// 👍 150 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //dp算法， 定义 f(i)为选取第i个元素的最大和值，g(i)为不选取第i个元素的最大和值，状态方程如下：
    // 选择i后，必定不能选择 i-1， 则 f(i) = g(i-1) + Si
    // 不选择i后， i-1可选可不选,  则 g(i) = max( f(i-1), g(i-1)
    // Max(f(n), g(n))即为题目结果
    public int massage(int[] nums) {
        if (nums==null || nums.length==0){
            return 0;
        }

        //定义初始值
        int fi = nums[0];
        int gi = 0;

        //自底向上计算
        for (int i = 1; i < nums.length; i++) {
            int next_fi = gi + nums[i];
            int next_gi = Math.max(fi, gi);

            fi = next_fi;
            gi = next_gi;
        }

        return Math.max(fi, gi);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
