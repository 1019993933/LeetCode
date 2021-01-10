package com.study.sort;

import org.junit.jupiter.api.Test;

/*
* 归并排序是一种分治算法，其核心是将N个已排序数组合并为一个，其中N==2为最常用的二路归并排序，也称合并排序
* 即对A[p, q],将其分解为A[p, r], A[r+1, q]
* 递归式如下：
*           A[p, q] = A[p]  p==q
*                   = merge(A[p, r], A[r+1, q] p<q, r = (p+q)/2
* */
public class MergeSort extends AbstractSort {
    @Override
    @Test
    public void Test() {
        doTest();
    }

    @Override
    public int[] sort(int[] arr, int p, int q) {
        if (p==q){
            return new int[]{arr[p]};
        }

        int r = (p+q)>>1;
        return merger(sort(arr, p, r), sort(arr, r+1, q));
    }

    private int[] merger(int[] d1, int[] d2) {
        int len = d1.length + d2.length;
        int [] result = new int[len];
        int i = d1.length-1, j = d2.length-1, k = len -1;

        while (k>=0){
            if (i>=0 && j>=0) {
                result[k--] = (d1[i] > d2[j]) ? d1[i--] : d2[j--];
            }
            else if (i>=0)
            {
                result[k--] = d1[i--];
            }
            else
            {
                result[k--] = d2[j--];
            }
        }

        return result;
    }
}
