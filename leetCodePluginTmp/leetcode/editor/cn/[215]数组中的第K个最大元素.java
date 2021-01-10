// 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
//
// 示例 1:
//
// 输入: [3,2,1,5,6,4] 和 k = 2
// 输出: 5
//
//
// 示例 2:
//
// 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
// 输出: 4
//
// 说明:
//
// 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
// Related Topics 堆 分治算法
// 👍 811 👎 0

// leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findKthLargest(int[] nums, int k) {
        return doFindKthLargest(nums, 0, nums.length - 1, nums.length-k+1);
    }

    public int doFindKthLargest(int[] A, int p, int q, int k) {
        if (p == q) {
            return A[p];
        }

        int r = partion(A, p, q);
        int leftLen =  r - p + 1;

        if (leftLen == k) {
            return A[r];
        } else if (leftLen < k) {
            return doFindKthLargest(A, r + 1, q, k - leftLen);
        } else {
            return doFindKthLargest(A, p, r - 1, k);
        }
    }

    public int partion(int[] A, int p, int q) {
        int i = p - 1;
        int key = A[q];
        for (int j = p; j < q; j++) {
            if (A[j] <= key) {
                ++i;
                exchange(A, j, i);
            }
        }
        exchange(A, i + 1, q);
        return i + 1;
    }

    public void exchange(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
// leetcode submit region end(Prohibit modification and deletion)
