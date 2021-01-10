package com.study.leetcode.solutions;

public class Solution {

    // 质数的特性是只能被1和其本身整除，也就是除2以外的偶数肯定都不是
    // 对于大于等于3的奇数n, 只需要在[3，m]的范围内遍历是否有数可以被n整除，如果存在则不是质数,
    // 其中m为n的平方根取整, 假设存在 m 和 m+k（k>=0)使得 m*(m+k) = n 则有m*m = n - m*k <= n,也就是 m<=n的平方根

    // 针对此题，参数n = 100
    public static void printPrime(int n) {
        if (n <= 1) {
            return;
        }

        // n大于等于2，则先打印2
        System.out.println(2);
        if (n == 2) {
            return;
        }

        // 上面已经处理了1和2的情况，此处循环从3开始到n找质数，同时步长为2跳过偶数
        for (int i = 3; i <= n; i += 2) {
            boolean isPrime = true;

            //整除判断时初始值从3开始判断，因为1不需要考虑，i为奇数也不需要考虑整除2的情况
            for (int j = 3; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    //找到了可以被整除的数，则不为质数
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                System.out.println(i);
            }
        }
    }
}
