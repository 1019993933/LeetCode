//实现一个算法，确定一个字符串 s 的所有字符是否全都不同。 
//
// 示例 1： 
//
// 输入: s = "leetcode"
//输出: false 
// 
//
// 示例 2： 
//
// 输入: s = "abc"
//输出: true
// 
//
// 限制： 
// 
// 0 <= len(s) <= 100 
// 如果你不使用额外的数据结构，会很加分。 
// 
// Related Topics 数组 
// 👍 63 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//方法一：先排序再遍历是否有相等
class Solution {
    public boolean isUnique(String astr) {
        long bits = 0;
        for (int i = 0; i < astr.length(); i++) {
            int move = astr.charAt(i) - 'A';
            if ((bits & (1L<<move))!=0)
            {
                return false;
            }

            bits |= (1L<<move);
        }

        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
