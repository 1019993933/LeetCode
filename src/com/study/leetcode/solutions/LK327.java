package com.study.leetcode.solutions;

public class LK327 {
    private int count;

    // 此题的所有解法都依赖于排序后的前缀和
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        return solution1(nums, lower, upper);
    }

    // 采用归并排序方法解决，注意此时并不是对原数组归并排序，对是对前缀各进行归并排序
    // 此处需要注意的是排序后的结果本身是不可利用的，因为其顺序已被排序破坏，我们此处利用的是排序过程中前后两次分区进顺序性，即在分组过程中
    // 后一组在顺序上总是在前一组之后的，但是合并后其顺序就已经打乱了

    // 对未排序的[0, len-1]分组为[0, k], [k+1, len], 对任意i属于【0， k], j属于[k+1， len], [i，j]总是构成一个连续的区间
    // 但是对于已经排序完成的[0, len-1]， 其中下标已经不具任何意义

    // 也就是说，对于sum[]数组，在未排序前，其中的下标即对应原数组前i对应的前缀和，但是排序完成后，下标就没法对应了

    // 类似可以利用此算法的是求逆数对

    // sum[i]表示前i个元素的各，我们要找的就是 sum[j]-sum[i]在区间[lower, upper] 内，此时[i,j]即为符合要求的区间对
    // 这个计数过程可以在归并排序的过程中，对结果进行统计
    public int solution1(int[] nums, int lower, int upper) {
        int[] sum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] += (nums[i] + sum[i]);
        }

        mergeSort(sum, 0, sum.length - 1, lower, upper);

        return count;
    }

    private void mergeSort(int[] nums, int start, int end, int lower, int upper) {
        if (start == end) {
            if (nums[start] >= lower && nums[start] <= upper) {
                ++count;
            }
            return;
        }

        int mid = (end + start) / 2;
        mergeSort(nums, start, mid, lower, upper);
        mergeSort(nums, mid + 1, end, lower, upper);

        merge(nums, start, mid, end, lower, upper);
    }

    // 合并的过程即包含了计数的过程
    private void merge(int[] nums, int start, int mid, int end, int low, int upper) {
        int i = start;
        int l = mid + 1;
        int r = mid + 1;
        while (i <= mid) {
            while (l <= end && nums[l] - nums[i] < low) {
                l++;
            }
            while (r <= end && nums[r] - nums[i] <= upper) {
                r++;
            }

            count += (r - l);
            ++i;
        }

        int[] sorted = new int[end - start + 1];
        int p1 = start;
        int p2 = mid + 1;
        int p = 0;
        while (p1 <= mid || p2 <= end) {
            if (p1 > mid) {
                sorted[p++] = nums[p2++];
            } else if (p2 > end) {
                sorted[p++] = nums[p1++];
            } else {
                sorted[p++] = (nums[p1] > nums[p2] ? nums[p2++] : nums[p1++]);
            }
        }

        System.arraycopy(sorted, 0, nums, start, sorted.length);
    }
}
