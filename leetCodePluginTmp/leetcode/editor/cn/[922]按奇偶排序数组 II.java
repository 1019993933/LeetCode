//给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。 
//
// 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。 
//
// 你可以返回任何满足上述条件的数组作为答案。 
//
// 
//
// 示例： 
//
// 输入：[4,2,5,7]
//输出：[4,5,2,7]
//解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
// 
//
// 
//
// 提示： 
//
// 
// 2 <= A.length <= 20000 
// A.length % 2 == 0 
// 0 <= A[i] <= 1000 
// 
//
// 
// Related Topics 排序 数组 
// 👍 183 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] sortArrayByParityII(int[] A) {
        int m = 0; //下一个待交换的偶数位置
        int n = 1; //下一个待交换的奇数位置

        for (; m < A.length; m+=2) {
            if (A[m]%2==1) //偶数位为奇数，需要交换
            {
                //循环找到下一个需要交换的奇数位置
                while (A[n] % 2 == 1) {
                    n += 2;
                }
                exchange(A, m, n);
            }
        }

        return A;
    }

    public void exchange(int[] data, int i, int j){
        assert i>=0 && i<data.length;
        assert j>=0 && j<data.length;

        int tmp = data[j];
        data[j] = data[i];
        data[i] = tmp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
