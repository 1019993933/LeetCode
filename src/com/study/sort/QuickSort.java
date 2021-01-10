package com.study.sort;

import org.junit.jupiter.api.Test;

public class QuickSort extends AbstractSort{
    @Test
    public void Test(){
        doTest();
    }


    @Override
    public int[] sort(int[] arr, int p, int q){
        if (p<q){
            int r = partition(arr, p, q);
            sort(arr, p, r-1);
            sort(arr, r+1, q);
        }

        return arr;
    }

    private int partition(int[] arr, int p, int q) {
        int i = p-1;
        int X = arr[q];
        for (int j = p; j < q; j++) {
            if (arr[j]<=X){
                ++i;
                exchange(arr, i, j);
            }
        }

        exchange(arr, i+1, q);
        return i+1;
    }
}
