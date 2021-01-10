package com.study.leetcode.solutions;

import java.util.ArrayList;

public class PrimeCalc {
    // 打印输出1~n范围内质数
    public void printPrim(int n)
        {
            ArrayList<Integer> data = calcPrime(n);
            if (data!=null)
            {
                for (Integer num: data)
                {
                    System.out.println(num);
                }
            }
        }

    // 计算质数列表
    public ArrayList<Integer> calcPrime(int n) {
        if (n <= 1 || n > 100) {
            return null;
        }

        ArrayList<Integer> data = new ArrayList<>();
        if (n>=2)
        {
            data.add(2);
        }

        int m = 1;
        while (m <= n) {
            if (isPrime(m)) {
                data.add(m);
            }

            // 偶数肯定不是，每次加2
            m += 2;
        }
        return data;
    }

    private boolean isPrime(int n)
        {
            if (n<=2){
                return n==2;
            }

            for (int i=3; i<=n; ++i){
                //计算平方根m, 从2到m如果能够整除，则不是质数
                int m = (int)Math.sqrt(i);
                for (int j=2; j<=m; ++j)
                {
                    if (n%j == 0)
                    {
                        return false;
                    }
                }
            }

            return true;
        }


        public void printPrime2(int n) {
            if (n < 2) {
                return;
            }

            System.out.println(2);

            for (int i = 3; i <= n; ++i) {
                // 计算平方根m, 从2到m如果能够整除，则不是质数
                int m = (int)Math.sqrt(i);
                boolean isPrime = true;
                for (int j = 2; j <= m; ++j) {
                    if (i % j == 0) {
                        isPrime = false;
                        break;
                    }
                }

                if (isPrime)
                    System.out.println(i);
            }
        }

        public static void test4() {
            boolean bool;
            for (int i = 3; i < 100; i += 2) {
                bool = true;
                for (int j = 3; j <= Math.sqrt(i); j++) {
                    if (i % j == 0) {
                        bool = false;
                        break;
                    }
                }
                if (bool)
                    System.out.print(i + " ");
            }
        }
    }
