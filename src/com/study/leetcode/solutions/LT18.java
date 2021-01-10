package com.study.leetcode.solutions;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LT18 {
    @Test
    public void test()
    {
        fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        System.out.println();
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Solution1 solution1 = new Solution1();
        List<List<Integer>> lists = solution1.fourSum(nums, target);
        return lists;
    }

    public class Solution1{
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        public List<List<Integer>> fourSum(int[] nums, int target) {
            if (nums==null || nums.length<4){
                return ans;
            }

            List<Integer> lst = new ArrayList<>();
            fourSum(0, 4, lst, nums, target);
            return ans;
        }

        public void fourSum(int startIndex, int num, List<Integer> curList, int[] nums, int target) {
            if (nums.length-startIndex<num || num<=0){
                return;
            }

            if (nums.length-startIndex==num){
                int sum = 0;
                List<Integer> lst = new ArrayList<>();
                for (int i = startIndex; i < nums.length; i++) {
                    lst.add(nums[i]);
                    sum += nums[i];
                }
                if (sum==target){
                    curList.addAll(lst);
                    ans.add(curList);
                }
                return;
            }

            List<Integer> newList = new ArrayList<>();
            newList.addAll(curList);
            fourSum(startIndex+1, num, newList, nums, target);
            newList = new ArrayList<>();
            newList.addAll(curList);
            newList.add(nums[startIndex]);
            fourSum(startIndex+1, num-1, newList, nums, target-nums[startIndex]);
        }
    }
}
