//输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [3,2,1], k = 2
//输出：[1,2] 或者 [2,1]
// 
//
// 示例 2： 
//
// 输入：arr = [0,1,2,1], k = 1
//输出：[0] 
//
// 
//
// 限制： 
//
// 
// 0 <= k <= arr.length <= 10000 
// 0 <= arr[i] <= 10000 
// 
// Related Topics 堆 分治算法 
// 👍 160 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        //通过快排取前k个数组
        return solution1(arr, k);
    }

    @org.jetbrains.annotations.NotNull
    //执行耗时:8 ms,击败了62.08% 的Java用户
    //			内存消耗:39.8 MB,击败了71.72% 的Java用户
    private int[] solution1(int[] arr, int k) {
        if (arr ==null || arr.length< k){
            return new int[0];
        }

        quickSort(arr, 0, arr.length-1);
        return Arrays.copyOf(arr, k);
    }

//    public int getLeastNumberIndex(int[] arr, int p, int r, int k)
//    {
//        int q = partition(arr, p, r);
//        if (q==k){
//            return q;
//        }
//        else if (q<k)
//        {
//            q = getLeastNumberIndex(arr, q+1, r, k-q);
//        }
//        else
//        {
//            q = getLeastNumberIndex((arr, p, q, k);
//        }
//
//        return q;
//    }


    public void quickSort(int[] A, int p, int r){
        if (p<r){
            int q = partition(A, p, r);
            quickSort(A, p, q-1);
            quickSort(A, q+1, r);
        }
    }

    public int partition(int[] A, int p, int r){
        int i = p-1;
        int x = A[r];
        for (int j = p; j < r; j++) {
            if (A[j]<x){
                ++i;
                swap(A, i, j);
            }
        }
        swap(A, i+1, r);
        return i+1;
    }

    public void swap(int[] A, int i, int j){
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }



    //通过最小堆排序取前k个数组
    private int[] solution2(int[] arr, int k) {
        if (arr ==null || arr.length< k){
            return new int[0];
        }

        quickSort(arr, 0, arr.length-1);
        return Arrays.copyOf(arr, k);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
