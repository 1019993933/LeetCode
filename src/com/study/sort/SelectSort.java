package com.study.sort;

import org.junit.jupiter.api.Test;

public class SelectSort extends AbstractSort {
    @Override
    @Test
    public void Test() {
        doTest();
    }

    @Override
    public int[] sort(int[] arr, int p, int q) {
        for (int i = p; i < q; i++) {
            int minIndex = i;
            for (int j = i+1; j <= q; j++) {
                if (arr[j]<arr[minIndex]){
                    minIndex = j;
                }
            }
            exchange(arr, i, minIndex);
        }
        return arr;
    }
}
