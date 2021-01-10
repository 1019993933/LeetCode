package com.study.leetcode.solutions;

import com.study.sort.AbstractSort;
import org.junit.jupiter.api.Test;

public class LK307_FenwickTree {
    private int[] C;
    private int[] originData;

    public LK307_FenwickTree(int[] nums) {
        C = new int[nums.length + 1];
        this.originData = nums;

        for (int i = 0; i < nums.length; i++) {
            doUpdate(i, nums[i]);
        }
    }

    //更新i位置的值为val,考虑到后面的维护，其实是要调用更新C[i]位置的差值
    public void update(int i, int val) {
        int delta = val - originData[i];
        doUpdate(i, delta);
    }

    private void doUpdate(int i, int delta){
        for (int j = i + 1; j <= C.length - 1; j += lowbit(j)) {
            C[j] += delta;
        }
    }

    public int sumRange(int i, int j) {
        return sum(j) - sum(i - 1);
    }

    // 将x二进制低位第一个1位置对应的高位置0后的值，其值为2^k
    // 如 lowbit(100010) = 10
    // lowbit(1) = 1
    // lowbit(1010110110) = 2
    private int lowbit(int x) {
        return x & (-x);
    }

    private int sum(int i) {
        int sum = 0;
        for (int j = i + 1; j > 0; j -= lowbit(j)) {
            sum += C[j];
        }
        return sum;
    }
}
