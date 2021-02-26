package com.study.leetcode;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class LK220 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums==null || nums.length<=1){
            return false;
        }

        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            Integer s = set.ceiling(key);
            if (s!=null && checkDistance(s, key, t)){
                return true;
            }
            s = set.floor(key);
            if (s!=null && checkDistance(key, s, t)){
                return true;
            }

            set.add(key);
            if (set.size()>k){
                set.remove(nums[i-k]);
            }
        }
        return false;
    }

    boolean checkDistance(int oLarger, int oSmall, int k) {
        int larger = Math.max(oLarger, oSmall);
        int small = Math.min(oLarger, oSmall);
        if (larger >= Integer.MIN_VALUE + k && larger<=Integer.MAX_VALUE-k) {
            return small >= larger - k && small <= larger +k;
        } else if (larger > Integer.MAX_VALUE - k){
            return small>= larger-k;
        } else {
            return small <= larger+k;
        }
    }

    @Test
    public void doTest(){
        int[] nums = new int[]{-3, 3, -6};
        LK220 obj = new LK220();
        boolean b = obj.containsNearbyAlmostDuplicate(nums, 2, 3);
        System.out.println(b);
    }
}
