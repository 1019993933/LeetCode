package com.study.sort;

import org.junit.jupiter.api.Test;

/**
 * xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
 *          ij
 * i之前的，表示已经排好序，j之后的，没有排序
 * 对一个新的i，从前面的i~0打到对应的位置插入，1）不用每次交换，如果k=i位置不满足，则k后移，最后一个满足
 *  arr[k]<key时，k+1即为key的位置
 *
 */
public class InsertSort extends AbstractSort {
    @Override
    @Test
    public void Test() {
        doTest();
    }

    @Override
    public int[] sort(int[] arr, int p, int q) {
        for (int i = p; i < q; i++) {
            int key = arr[i+1];
            int j = i;
            while(j>=0 && arr[j]>key){
                arr[j+1] = arr[j];
                --j;
            }
            arr[j+1] = key;
        }
        return arr;
    }
}
