//编写一个函数，以字符串作为输入，反转该字符串中的元音字母。 
//
// 
//
// 示例 1： 
//
// 输入："hello"
//输出："holle"
// 
//
// 示例 2： 
//
// 输入："leetcode"
//输出："leotcede" 
//
// 
//
// 提示： 
//
// 
// 元音字母不包含字母 "y" 。 
// 
// Related Topics 双指针 字符串 
// 👍 139 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseVowels(String s) {
        if (s==null || s.length()<=1){
            return s;
        }

        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length-1;
        while (i<j){
            if (!isYuanYin(chars[i])){
                ++i;
                continue;
            }
            if (!isYuanYin(chars[j])){
                --j;
                continue;
            }

            if (i<j){
                swapCh(chars, i++, j--);
            }
        }

        return String.valueOf(chars);
    }

    private boolean isYuanYin(char ch){
        return ch=='a' || ch=='o' || ch=='i' || ch=='e' || ch=='u' || ch=='A' || ch=='O' || ch=='I' || ch=='E' || ch=='U';
    }

    private void swapCh(char[] chars, int i, int j){
        char ch = chars[i];
        chars[i] = chars[j];
        chars[j] = ch;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
