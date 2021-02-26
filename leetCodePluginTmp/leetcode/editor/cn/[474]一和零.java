//给你一个二进制字符串数组 strs 和两个整数 m 和 n 。 
//
// 
// 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。 
//
// 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
//输出：4
//解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
//其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 
//n 的值 3 。
// 
//
// 示例 2： 
//
// 
//输入：strs = ["10", "0", "1"], m = 1, n = 1
//输出：2
//解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 600 
// 1 <= strs[i].length <= 100 
// strs[i] 仅由 '0' 和 '1' 组成 
// 1 <= m, n <= 100 
// 
// Related Topics 动态规划 
// 👍 320 👎 0


import java.util.Arrays;
import java.util.Comparator;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs==null){
            return 0;
        }

        Arrays.sort(strs, (String o1, String o2) -> {
                    return o1.length() - o2.length();
                }
        );

        return findMaxFromSorted(strs, 0, m, n);
    }

    private int findMaxFromSorted(String[] strs, int startIndex, int m, int n){
        if (startIndex>=strs.length){
            return 0;
        }

        if (strs[startIndex].length()>m+n){
            return 0;
        }
        int oneCount = countOne(strs[startIndex]);
        int zeroCount = strs[startIndex].length() - oneCount;
        if (zeroCount>m || oneCount>n){
            return findMaxFromSorted(strs, startIndex+1, m, n);
        }else if (zeroCount==m && oneCount==n){
            return 1;
        } else{
            int maxFromSorted = findMaxFromSorted(strs, startIndex + 1, m - zeroCount, n - oneCount);
//            if (maxFromSorted>0){
                return Math.max(1+maxFromSorted, findMaxFromSorted(strs, startIndex+1, m, n));
//            } else
//            {
//                return findMaxFromSorted(strs, startIndex+1, m, n);
//            }
        }
    }

    private int countOne(String str){
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i)=='1'){
                ++count;
            }
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
