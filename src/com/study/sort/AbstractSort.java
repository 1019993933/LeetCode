package com.study.sort;

import java.util.Random;

public abstract class AbstractSort {
    /**
     * 是否采用随机数组
     */
    protected boolean randomData = false;

    public final static int[] data = new int[] {12, 3, 4590, 1021, 934, 3};

    public void printArr(int[] data) {
        if (data == null || data.length == 0) {
            System.out.println("This array is empty!");
        }

        for (int i = 0; i < data.length; i++) {
            System.out.printf("%d ", data[i]);
        }
        System.out.println();
    }

    public void exchange(int[] data, int i, int j) {
        assert i >= 0 && i < data.length;
        assert j >= 0 && j < data.length;

        int tmp = data[j];
        data[j] = data[i];
        data[i] = tmp;
    }

    public void doTest() {
        int[] arr = randomData ? TestData.generateArray() : data;
        printArr(arr);
        int[] result = sort(arr, 0, arr.length - 1);
        printArr(result);
        check(result, 0, result.length - 1);
        System.out.println("Finished Sort! " + this.getClass().getName());
    }

    protected boolean initRandomFlag(boolean randomFlag) {
        boolean oldFlag = randomData;
        this.randomData = randomFlag;
        return oldFlag;
    }

    public abstract void Test();

    public abstract int[] sort(int[] arr, int p, int q);

    /**
     * 检查数组是否是有序
     * 
     * @param arr
     * @param p
     * @param q
     * @return
     */
    protected boolean check(int[] arr, int p, int q) {
        int i = p + 1;
        while (i <= q) {
            if (arr[i] < arr[i - 1]) {
                System.out
                    .println(String.format("当前数组不是有序的！！！！序号[%d, %d], Value=[%d, %d]", i, i - 1, arr[i], arr[i - 1]));
                return false;
            }
            ++i;
        }

        System.out.println("当前数组是增序的");

        return true;
    }

    /**
     * 测试数据辅助类
     */
    public static class TestData {
        public static int[] generateArray() {
            Random random = new Random();

            int size = 100 + random.nextInt() % 100;
            int[] data = new int[size];
            for (int i = 0; i < size; i++) {
                data[i] = (int)(random.nextDouble() * 1000);
            }
            return data;
        }

        public static int[] generateFixArray() {
            return AbstractSort.data;
        }
    }

}
