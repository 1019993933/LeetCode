package com.study.leetcode.solutions;

import com.study.sort.AbstractSort;
import org.junit.jupiter.api.Test;

public class LK307Test {
    @Test
    public void dotest()
    {
        int[] data = AbstractSort.TestData.generateFixArray();
        LK307_FenwickTree obj = new LK307_FenwickTree(new int[]{7,2,7,2,0});

        obj.update(4, 6);
        obj.update(1, 2);
        obj.update(1, 2);
        int sum = obj.sumRange(0, 2);
        obj.update(1, 2);
        sum = obj.sumRange(0, 2);
        obj.update(1, 2);
        obj.update(1, 2);obj.update(1, 2);
        obj.update(1, 2);
        obj.update(1, 2);

        System.out.println(sum);
    }
}
