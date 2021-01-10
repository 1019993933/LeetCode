//给定字符串 s 和 t ，判断 s 是否为 t 的子序列。 
//
// 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。 
//
// 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"ae
//c"不是）。 
//
// 示例 1: 
//s = "abc", t = "ahbgdc" 
//
// 返回 true. 
//
// 示例 2: 
//s = "axc", t = "ahbgdc" 
//
// 返回 false. 
//
// 后续挑战 : 
//
// 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码
//？ 
//
// 致谢: 
//
// 特别感谢 @pbrother 添加此问题并且创建所有测试用例。 
// Related Topics 贪心算法 二分查找 动态规划 
// 👍 361 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isSubsequence(String s, String t) {
        if (t==null){
            return s==null;
        }

        if (s==null || s.equals(t)){
            return true;
        }

        if (s.length()>t.length()){
            return false;
        }

        //S:  s的字符串序列 Si表示<S1,S2...Si>
        //T： t的字符序列，Tj表示<T1,T2...Tj>
        //假设Tj为Si的子串， if Si==Tj, 则 Tj-1为Si-1的子串
        //                 if Si!=Tj, 则 Tj为Si-1的子串
        return isSubSeq(s, s.length()-1, t, t.length()-1);
    }

    public boolean isSubSeq(String s, int m, String t, int n){
        if (m<0 || n<0){ //一个字符序列已经匹配完成
            return (m<0)? true: false; //m<0表示 S序列已经匹配完，意味着是一个子串
        }

        if (s.charAt(m) == t.charAt(n)){
            return isSubSeq(s, m-1, t, n-1);
        }
        else{
            return isSubSeq(s, m, t, n-1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
