package com.study.leetcode.solutions;

import com.study.leetcode.LK220;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class LK352 {
    TreeMap<Integer, Integer> map = new TreeMap<>();

    /** Initialize your data structure here. */
    public LK352() {

    }

    public void addNum(int val) {
        if (!map.containsKey(val)){
            Map.Entry<Integer, Integer> ceilingEntry = map.ceilingEntry(val);
            Map.Entry<Integer, Integer> floorEntry = map.floorEntry(val);

            if (floorEntry!=null && floorEntry.getValue()==val-1 && ceilingEntry!=null && ceilingEntry.getKey()-1==val){
                map.put(floorEntry.getKey(), ceilingEntry.getValue());
                map.remove(ceilingEntry.getKey());
            } else if (floorEntry!=null && floorEntry.getValue()==val-1){
                map.put(floorEntry.getKey(), val);
            } else if (ceilingEntry!=null && ceilingEntry.getKey()-1==val){
                map.put(val, ceilingEntry.getValue());
                map.remove(ceilingEntry.getKey());
            } else{
                map.put(val, val);
            }
        }
    }

    public int[][] getIntervals() {
        if (map.size()==0){
            return new int[][]{};
        }

        int[][] ans = new int[map.size()][2];
        int i = 0;
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            ans[i][0] = entry.getKey();
            ans[i][1] = entry.getValue();
            ++i;
        }

        return ans;
    }

    @Test
    public void doTest(){
        LK352 obj = new LK352();
        int[] nums = new int[]{6, 6, 0, 4, 8, 7,  6, 4, 7, 5};
        for (int num : nums) {
            obj.addNum(num);
            int[][] param_2 = obj.getIntervals();
            for (int[] ints : param_2) {
                System.out.print(Arrays.toString(ints));
            }
            System.out.println();
        }
    }
}
