// 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
//
// 说明：
//
//
// 拆分时可以重复使用字典中的单词。
// 你可以假设字典中没有重复的单词。
//
//
// 示例 1：
//
// 输入: s = "leetcode", wordDict = ["leet", "code"]
// 输出: true
// 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
//
//
// 示例 2：
//
// 输入: s = "applepenapple", wordDict = ["apple", "pen"]
// 输出: true
// 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
//   注意你可以重复使用字典中的单词。
//
//
// 示例 3：
//
// 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
// 输出: false
//
// Related Topics 动态规划
// 👍 771 👎 0

import java.util.HashSet;
import java.util.Set;

// leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //状态方程：定义 dp[i] 为前i个字符是否能被字典分割，则对第i+1个字符
    // 如果 dp[i-1] == true 且 字典包含第i个字符，则 dp[i] = true;
    // 否则向前查找下一个 dp[k] == true, 判断 subStr(k, i)是否被字典包含，如果包含则 dp[i] = true
    // 如果 k < 0，即一直找不到可包含的，则 dp[i] = false

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {

            //解答成功:
            //		执行耗时:4 ms,击败了85.63% 的Java用户
            //		内存消耗:38.6 MB,击败了70.28% 的Java用户

            //解答成功:
            //		执行耗时:10 ms,击败了42.25% 的Java用户
            //		内存消耗:38.8 MB,击败了57.87% 的Java用户

            //此处的j有升序和降序两种方式，当前测试用例采用降序性能更好一点

            for (int j = i-1; j >=0 ; j--) {
            //for (int j = 0; j <= i - 1 ; j++) {
                String subStr = s.substring(j, i);
                if (dp[j] && wordDictSet.contains(subStr))
                {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }
}
// leetcode submit region end(Prohibit modification and deletion)
