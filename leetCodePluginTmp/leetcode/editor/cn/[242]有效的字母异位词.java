//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 
//
// 示例 1: 
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "rat", t = "car"
//输出: false 
//
// 说明: 
//你可以假设字符串只包含小写字母。 
//
// 进阶: 
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 排序 哈希表 
// 👍 311 👎 0


import org.junit.jupiter.api.Test;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isAnagram(String s, String t) {
        return solution2(s, t);
    }

    //假设是只包含小写字母的字符串，可以采用数组来存储每位字母的数量，数组下标对应字母
    private boolean solution2(String s, String t) {
        if (s ==null && t == null){
            return true;
        }
        else if (s.length()!= t.length()){
            return false;
        }

        int[] d1 = new int['z'-'a'+1];
        int[] d2 = new int['z'-'a'+1];
        for (int i = 0; i < s.length(); i++) {
            d1[s.charAt(i)-'a'] += 1;
            d2[t.charAt(i)-'a'] += 1;
        }

        return Arrays.equals(d1, d2);
    }

    //先排序再比较
    private boolean solution1(String s, String t) {
        if (s ==null && t == null){
            return true;
        }
        else if (s.length()!= t.length()){
            return false;
        }

        char[] d1 = sort(s.toCharArray(), 0, s.length()-1);
        char[] d2 = sort(t.toCharArray(), 0, t.length()-1);

        return Arrays.equals(d1, d2);
    }

    public char[] sort(char[] arr, int p, int q){
        if (p<q){
            int r = partition(arr, p, q);
            sort(arr, p, r-1);
            sort(arr, r+1, q);
        }
        return arr;
    }

    private int partition(char[] arr, int p, int q) {
        int i = p -1;
        char key = arr[q];
        for (int j = p; j < q; j++) {
            if (arr[j]<=key){
                ++i;
                exchange(arr, i, j);
            }
        }
        exchange(arr,i+1, q);
        return i+1;
    }

    public void exchange(char[] data, int i, int j){
        assert i>=0 && i<data.length;
        assert j>=0 && j<data.length;

        char tmp = data[j];
        data[j] = data[i];
        data[i] = tmp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
