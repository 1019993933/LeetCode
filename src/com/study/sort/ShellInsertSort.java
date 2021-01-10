package com.study.sort;

import org.junit.jupiter.api.Test;

public class ShellInsertSort extends AbstractSort{

    @Test
    public void Test() {
        boolean odlFlag = initRandomFlag(true);
        doTest();
        initRandomFlag(odlFlag);
    }

    /**
     * 希尔排序，分组插入排序，原理是插入排序效率与原始序列的初始有序性成正比
     * 希尔排序通过grap将初始序列进行分组，对同一组的进行简单插入排序，再减少grap值重复进行插入排序，直至grap退化为1，完成
     * 即最后一轮总是一次简单插入排序，但是由于前面的分组操作，导致该次简单插入排序效率较之直接针对原始序列插入排序要高
     *
     * 希尔排序的效率与grap的选择有很大关系，在最坏的情况下，可能前面的分组操作都没有进行任何有效调整，最终效率差于简单插入
     *
     * 希尔增量：{N/2, (N / 2)/2, ..., 1} N==size， 即二分增量
     *
     * 目前较好的grap算法有两种：
     * Hibbard增量： 2^k -1, 即1， 3， 5， 7， 15..。。O(n^(3/2))
     * Hibbard：{1, 3, ..., 2^k-1}，  O(n^(3/2))
     * Sedgewick：{1, 5, 19, 41, 109...}该序列中的项或者是9*4^i - 9*2^i + 1或者是4^i - 3*2^i + 1  O(n^(4/3))
     * const unsigned long Sedgewick[] =
     *     {1, 5, 19, 41, 109, 209, 505, 929, 2161,
     *         3905, 8929, 16001, 36289, 64769, 146305,
     *         260609, 587521, 1045505, 2354689, 4188161,
     *         9427969, 16764929, 37730305, 67084289,
     *         150958081, 268386305, 603906049, 1073643521};
     *
     *
     * 希尔排序中由于相同元素可能分在不同的组，所以该排序是不稳定的，原地排序
     *
     * @param arr
     * @param p
     * @param q
     * @return
     */
    @Override
    public int[] sort(int[] arr, int p, int q) {
        if (p>=q){
            return arr;
        }

        for (int grap = (q-p)/2; grap > 0 ; grap>>=1) {
            System.out.println("grap = " + grap);
//            printArr(arr);
            for (int i = p+grap; i <= q; i++) {
                int j = i;
                while (j-grap>=0) {
                    if (arr[j] < arr[j - grap]) {
                        exchange(arr, j, j - grap);  //这是交换法
                    }
                    j = j-grap;
                }
            }
        }

        return arr;
    }

}
