//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 示例 1: 
//
// 输入: "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window 
// 👍 4602 👎 0


import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    HashSet<Character> cache = new HashSet<>();

    public int lengthOfLongestSubstring(String s) {
        if (s==null) return 0;
        if (s.length()<=1) return s.length();

        //此题适合用滑动窗口来解
        int maxNum = 0;
        int i = 0;
        int j = 0;
        while (i<s.length()-maxNum && j<s.length()) //结束条件是 i到结尾的长度大于max, 且j没有到结尾
        {
            //cache实际缓存的是与窗口对应的不重复字符串
            if (!cache.contains(s.charAt(j)))
            {
                cache.add(s.charAt(j));
                if (j-i+1>maxNum)
                {
                    //由于连续性，最大长度肯定是连续增长的，+1即可，其实也等于此刻的 j-i+1
                    ++maxNum;
                }
                ++j;
            }
            else
            {
                //j后移一位后，发生重复，则i后移一位，并重新判断j
                cache.remove(s.charAt(i));
                ++i;
                // j位置不变，要与前面重新判断是否重复，没有重复才可以后移
                // ++j;
            }
        }

        return maxNum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
