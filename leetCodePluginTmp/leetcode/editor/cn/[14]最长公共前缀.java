//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 示例 1: 
//
// 输入: ["flower","flow","flight"]
//输出: "fl"
// 
//
// 示例 2: 
//
// 输入: ["dog","racecar","car"]
//输出: ""
//解释: 输入不存在公共前缀。
// 
//
// 说明: 
//
// 所有输入只包含小写字母 a-z 。 
// Related Topics 字符串 
// 👍 1351 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs==null || strs.length==0)
        {
            return "";
        }

        StringBuffer str = new StringBuffer();


        int j = 0;
        while(strs[0].length()>j)
        {
            char ch = strs[0].charAt(j);
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].length()<=j || strs[i].charAt(j)!=ch)
                {
                    return str.toString();
                }
            }
            str.append(ch);
            ++j;
        }

        return str.toString();

    }
//
//    public String findTwoStringlongestCommonprefix(String str1, String str2)
//    {
//        if (str1==null || str1=="" || str2==null || str2=="")
//        {
//            return "";
//        }
//
//        int i = 0;
//        while(str1.charAt(i)==str2.charAt(i))
//        {
//            ++i;
//        }
//
//
//        return (i>0)? str1.substring(0, i): "";
//    }
}
//leetcode submit region end(Prohibit modification and deletion)
