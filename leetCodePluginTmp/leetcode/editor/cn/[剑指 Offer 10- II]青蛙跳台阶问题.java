//一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。 
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。 
//
// 示例 1： 
//
// 输入：n = 2
//输出：2
// 
//
// 示例 2： 
//
// 输入：n = 7
//输出：21
// 
//
// 示例 3： 
//
// 输入：n = 0
//输出：1 
//
// 提示： 
//
// 
// 0 <= n <= 100 
// 
//
// 注意：本题与主站 70 题相同：https://leetcode-cn.com/problems/climbing-stairs/ 
//
// 
// Related Topics 递归 
// 👍 85 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//分析问题有 f(0) = 1, f(1) = 1, f(2) = 2; f(n) = f(n-1) + f(n-2){n>=2},其实就是一个斐波那契函数
class Solution {
    public int numWays(int n) {
        int c1 = 1, c2 = 1, sum = 0;
        for (int i = 0; i < n; i++) {
           sum = (c1 + c2) % 1000000007;
           c1 = c2;
           c2 = sum;
        }
        return c1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
