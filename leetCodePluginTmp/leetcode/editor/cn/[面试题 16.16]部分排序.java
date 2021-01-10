//给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。注意：n-m尽量最小，也就是说，找出符合条件的最短
//序列。函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。 
// 示例： 
// 输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
//输出： [3,9]
// 
// 提示： 
// 
// 0 <= len(array) <= 1000000 
// 
// Related Topics 排序 数组 
// 👍 51 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] subSort(int[] array) {
        return solution2(array);

    }

    private int[] solution2(int[] array) {
        return solution1(array);
    }

    //info
    //			解答成功:
    //			执行耗时:22 ms,击败了15.51% 的Java用户
    //			内存消耗:62.7 MB,击败了45.04% 的Java用户
    //
    private int[] solution1(int[] array) {
        if (array ==null || array.length<=1){
            return new int[]{-1, -1};
        }

        int[] dst = Arrays.copyOf(array, array.length);
        Arrays.sort(dst);
        int m = -1;
        int n = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i]!=dst[i]){
                m = i;
                break;
            }
        }

        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] != dst[i]){
                n = i;
                break;
            }
        }

        if (m==-1 || n==-1){
            return new int[]{-1, -1};
        }
        else{
            return new int[]{m, n};
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
