package com.study.sort;

import org.junit.jupiter.api.Test;

/*
* 堆： 完全二叉树，平衡二叉树，最大堆与最小堆，算法复杂度O(nlogn)
* */
public class HeapSort extends AbstractSort {
    @Override
    @Test
    public void Test() {
        doTest();
    }

    @Override
    public int[] sort(int[] arr, int p, int q) {
        while(p<q) {
            buildHeap(arr, p, q);
            exchange(arr, p, q);
            --q;
        }
        return arr;
    }

    //构建从p到q的最大堆
    private void buildHeap(int[] arr, int p, int q) {
        int nonLeaf = ((q-p+1)>>1) - 1; // (n/2)
        while(nonLeaf>=0){
            maxHeapify(arr, p, q, nonLeaf);
            --nonLeaf;
        }
    }

    private void maxHeapify(int[] arr, int p, int q, int nonLeaf) {
        if (nonLeaf<p || nonLeaf>q){
            return;
        }

        int left = nonLeaf*2 + 1;
        int right = (nonLeaf+1)*2;
        if (left>q && right>q){
            return;
        }

        int largest = nonLeaf;
        if (left<=q && arr[left]>arr[largest])
        {
            largest = left;
        }

        if (right<=q && arr[right]>arr[largest])
        {
            largest = right;
        }

        if (largest!=nonLeaf)
        {
            exchange(arr, nonLeaf, largest);
            maxHeapify(arr, p, q, largest);
        }
    }
}


