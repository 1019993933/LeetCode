package com.study.leetcode.solutions;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class NC38Test {

    @org.junit.jupiter.api.Test
    void spiralOrder() {
        NC38 obj = new NC38();

        int[][] matrix =new int[][]{{1},{2},{3}};

        ArrayList<Integer> integers = obj.spiralOrder(matrix);

        for (Integer data: integers
             ) {
            System.out.println(data);
        }
    }

    @org.junit.jupiter.api.Test
    void doPrimeCalc() {
        PrimeCalc obj = new PrimeCalc();
//        obj.printPrime2(100);
//
//        obj.test4();


        Solution.printPrime(100);
    }

    @org.junit.jupiter.api.Test
    void testOf61()
    {
        int[] nums = new int[]{11,8,12,8,10};
        isStraight(nums);
    }

    public boolean isStraight(int[] nums) {
        int sum = 0;

        HashSet<Integer> set = new HashSet<Integer>(){
            {
                add(0x7);
                add(0x13);
                add(0x19);
                add(0x1C);
                add(0xF);
                add(0x17);
                add(0x1B);
                add(0x1D);
                add(0x1F);
            }};

        // 5个数可能重复，重复为false
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]!=0)
            {
                sum |= 1<<nums[i];
            }
        }

        while (sum%2==0){
            sum >>= 1;
        }

        return set.contains(sum);

    }
}