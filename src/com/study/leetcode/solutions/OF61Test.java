package com.study.leetcode.solutions;

public class OF61Test {
    @org.junit.jupiter.api.Test
    void test() {
        int[] nums = new int[]{4,7,5,9,2};
        isStraight(nums);
    }

    public boolean isStraight(int[] nums) {
//        int sum = 0;
//
//        HashSet<Integer> set = new HashSet<>(){
//            {
//                add(0x7);
//                add(0x13);
//                add(0x19);
//                add(0x1C);
//                add(0xF);
//                add(0x17);
//                add(0x1B);
//                add(0x1D);
//                add(0x1F);
//            }};
//
//        // 5个数可能重复，重复为false
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i]!=0)
//            {
//                sum |= 1<<nums[i];
//            }
//        }
//
//        while (sum%2==0){
//            sum >>= 1;
//        }
//
//        return set.contains(sum);

        insertSort(nums);
        if (isRepeatNum(nums))
        {
            return false;
        }
        int i=0;
        while(i<5)
        {
            if (nums[i]!=0) {
                return nums[4] - nums[i] < 5;
            }
            ++i;
        }
        return false;

    }

    public boolean isRepeatNum(int[] nums)
    {
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i+1]==nums[i] && nums[i]!=0)
            {
                return true;
            }
        }
        return false;
    }

    public void insertSort(int[] nums)
    {
        for (int i = 0+1; i < nums.length; i++) {
            int x  = nums[i];
            for (int j = i; j > 0; j--) {
                if (nums[j]>=nums[j-1])
                {
                    break;
                }
                swap(nums, j, j-1);
            }
        }

    }

    private void swap(int[] nums, int i, int j)
    {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    @org.junit.jupiter.api.Test
    public void NC392Test()
    {
        isSubSeq("abc", 2, "ahbgdc", 5);
    }
    public boolean isSubSeq(String s, int m, String t, int n){
        if (m<0 && n>=m){
            return true;
        }

        if (n<0 || n<m){
            return false;
        }

        if (s.charAt(m) == t.charAt(n)){
            return isSubSeq(s, m-1, t, n-1);
        }
        else{
            return isSubSeq(s, m, t, n-1);
        }
    }
}
