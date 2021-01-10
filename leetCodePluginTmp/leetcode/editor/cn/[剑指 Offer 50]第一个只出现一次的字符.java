//在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。 
//
// 示例: 
//
// s = "abaccdeff"
//返回 "b"
//
//s = "" 
//返回 " "
// 
//
// 
//
// 限制： 
//
// 0 <= s 的长度 <= 50000 
// Related Topics 哈希表 
// 👍 57 👎 0


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public char firstUniqChar(String s) {
        return solution1(s);
    }

    //采用HashMap进行缓存
    //          执行耗时:28 ms,击败了48.31% 的Java用户
    //			内存消耗:38.6 MB,击败了95.67% 的Java用户
    private char solution1(String s) {
        if (s ==null || s.length()==0){
            return ' ';
        }

        if (s.length()==1){
            return s.charAt(0);
        }

        HashMap<Character, Integer> cache = new HashMap<>(32);
        for (char ch: s.toCharArray()) {
            cache.put(ch, cache.containsKey(ch)? 2: 1);
        }

        for (char ch: s.toCharArray()) {
            if (cache.get(ch)==1)
            {
                return ch;
            }
        }

        return ' ';
    }

    //思路将原字符串排序，再比较排序后的字符串序列，第一个连续不相等的字符即为结果
    // 这种实现有问题，找到的只是不重复，但不是第一个
    private char solution2(String s) {
        if (s ==null || s.length()==0){
            return ' ';
        }

        if (s.length()==1){
            return s.charAt(0);
        }

        char [] result = s.toCharArray();

        //排序算法
        sort(result);
        for (int i = 0; i < result.length-1; i++) {
            if (result[i] != result[i+1])
            {
                return result[i];
            }
        }

        return ' ';
    }

    public void sort(char[] arr)
    {
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j]>=arr[j-1]){
                    break;
                }

                char ch = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = ch;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
