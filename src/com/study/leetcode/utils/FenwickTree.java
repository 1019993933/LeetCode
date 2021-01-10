package com.study.leetcode.utils;

public class FenwickTree {
    private int n;
    private int[] tree;

    public FenwickTree(int size){
        this.n = size + 2;
        tree = new int[this.n];
    }

    public void update(int i, int val){
        while(i<n){
            tree[i] += val;
            i += lowbit(i);
        }
    }

    public long sum(int i){
        long sum = 0;
        while(i>0){
            sum += (long)tree[i];
            i -= lowbit(i);
        }
        return sum;
    }

    private int lowbit(int x){
        return x & (-x);
    }
}

