package com.study.leetcode.utils;

public class IntervalTreeTest {
    public static void main(String[] args) {
        int[] nums = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        double log = Math.log(nums.length) / Math.log(2);
        int size = (int)Math.pow(2, (int)log + 2);
        IntervalTree tree = new IntervalTree(nums);

        check(tree, nums, 0, 9);
        update(tree, nums, 0, 9, 20);
//        check(tree, nums, 0, 9);

        check(tree, nums, 1, 1);
        update(tree, nums, 2, 3, 12);
        check(tree, nums, 1, 1);

        check(tree, nums, 1, 2);
        update(tree, nums, 2, 3, 37);
        check(tree, nums, 1, 2);

        check(tree, nums, 3, 3);
        check(tree, nums, 3, 3);

        check(tree, nums, 2, 15);
        check(tree, nums, 2, 15);
        check(tree, nums, 3, 5);
        check(tree, nums, 3, 5);
        check(tree, nums, 2, 7);
        check(tree, nums, 2, 7);
        check(tree, nums, 2, 3);

        System.out.println();
    }

    private static void update(IntervalTree tree, int[] nums, int left, int right, int val)
    {
        update(nums, left, right, val);
        tree.update(left, right, val);
    }

    /**
     * 测试求区间和
     *
     * @param tree
     * @param nums
     * @param left
     * @param right
     */
    private static void check(IntervalTree tree, int[] nums, int left, int right) {
        long rangeSum = tree.getRangeSum(left, right);
        long sum = getSum(nums, left, right);
        System.out.println(String.format("%s: %d = %d", rangeSum != sum ? "Error" : "OK", sum, rangeSum));
    }

    private static long getSum(int[] nums, int left, int right) {
        long sum = 0;
        for (int i = left; i <= right; i++) {
            if (i<0 || i>=nums.length){continue;}
            sum += nums[i];
        }
        return sum;
    }

    private static void update(int[] nums, int left, int right,  int val){
        for (int i = left; i <= right; i++) {
            if (i<0 || i>=nums.length){continue;}
            nums[i] += val;
        }
    }
}
