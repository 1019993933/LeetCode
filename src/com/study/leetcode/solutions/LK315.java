package com.study.leetcode.solutions;

import org.junit.jupiter.api.Test;

import java.util.*;

public class LK315 {

    @Test
    public void doTest()
    {
        int[] data = new int[]{-1,-2};

        solutionWithFenwickTree(data);
    }


    private List<Integer> solution1(int[] nums) {
        //1： 原数组中的值对应桶的下标（假设原数组中没有负数），求原数组中最大值，这个就可以知道需要用多少个桶\
        int maxValue = Arrays.stream(nums).max().getAsInt();
        int [] bucket = new int[maxValue+1]; //桶初始化完成后，对原数组任一元素nums[i], bucket[nums[i]]即表示该元素的个数，此处的思想可以参考计数排序

        // 存放结果的数组
        Integer[] count = new Integer[nums.length];
        for (int i = nums.length-1; i >= 0; i--) {
            int curCount = 0;

            //对桶该下标前面的元素求和，即为小于i位置的元素数量（这些元素都是在i之后的，第一重循环保证了这一点）
            for (int j = 0; j < nums[i]; j++) {
                curCount += bucket[j];
            }
            //将当前的值加到桶中
            bucket[nums[i]] += 1;
            count[i] = curCount;
        }

        return Arrays.asList(count);
    }

    private List<Integer> solutionWithFenwickTree(int[] nums) {
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        HashMap<Integer, Integer> values = new HashMap<>();
        int idx = 0;
        for(int x: set){
            values.put(x, idx++);
        }

        BitTree tree = new BitTree(values.size()+1);
        List<Integer> lst = new ArrayList<>();

        Integer[] count = new Integer[nums.length];

        int left = 0;
        for (int i = nums.length-1; i >= 0; i--) {
            // 原始序号与树状数组序号存在差1的关系，即原始序号0对应树状数组序号1
            // 对树状数组序号1取值，其含义为树数组（第一个元素桶序号0）的前缀和即为tree[1]={tree[0]+...+tree[1-1]} = tree[0] = 0(第一个元素前面没有元素，其前面的和自然为0)
            // 树状数组序号为 nums.length，对应桶数组最后一个元素nums.length-1的前缀和
            idx = values.get(nums[i]);
            int treeIdx =  idx + 1;
            count[i] = tree.query(treeIdx);
            tree.update(treeIdx+1, 1);
        }

        return Arrays.asList(count);
    }

    public class BitTree{
        private int[] tree;
        private int n;

        public BitTree(int n){
            this.n = n+1;
            this.tree = new int[this.n];
        }

        public void update(int i, int val){
            while(i<n){
                tree[i] += val;
                i += lowbit(i);
            }
        }

        public int query(int i){
            int ans = 0;
            while(i>0){
                ans += tree[i];
                i -= lowbit(i);
            }

            return ans;
        }

        private int lowbit(int x){
            return (x & (-x));
        }
    }


}
