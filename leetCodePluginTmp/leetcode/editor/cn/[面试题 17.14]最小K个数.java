//设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。 
//
// 示例： 
//
// 输入： arr = [1,3,5,7,2,4,6,8], k = 4
//输出： [1,2,3,4]
// 
//
// 提示： 
//
// 
// 0 <= len(arr) <= 100000 
// 0 <= k <= min(100000, len(arr)) 
// 
// Related Topics 堆 排序 分治算法 
// 👍 33 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] smallestK(int[] arr, int k) {
        if (k==0){
            return new int[0];
        }
        if (k==arr.length){
            return arr;
        }

        return Arrays.copyOf(quickSort(arr, 0, arr.length-1), k);
    }

    public int[] quickSort(int[] arr, int p, int q)
    {
        if (p<q) {
            int r = partion(arr, p, q);
            quickSort(arr, p, r - 1);
            quickSort(arr, r + 1, q);
        }
        return arr;
    }

    public int partion(int[] arr, int p, int q)
    {
        int i = p - 1;
        int key = arr[q];
        for (int j = p; j < q; j++) {
            if (arr[j]<key){
                ++i;
                swap(arr, i, j);
            }
        }
        swap(arr, i+1, q);
        return i+1;
    }

    public void swap(int[] arr, int p, int q){
        int tmp = arr[p];
        arr[p] = arr[q];
        arr[q] = tmp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
