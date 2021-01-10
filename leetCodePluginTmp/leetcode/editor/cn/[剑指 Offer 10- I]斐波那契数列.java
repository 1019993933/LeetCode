// 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
//
// F(0) = 0,   F(1) = 1
// F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
//
// 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
//
//
//
// 示例 1：
//
// 输入：n = 2
// 输出：1
//
//
// 示例 2：
//
// 输入：n = 5
// 输出：5
//
//
//
//
// 提示：
//
//
// 0 <= n <= 100
//
//
// 注意：本题与主站 509 题相同：https://leetcode-cn.com/problems/fibonacci-number/
// Related Topics 递归
// 👍 70 👎 0

import java.util.HashMap;
import java.util.HashSet;

// leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int fib(int n) {
        // return simpleSolution(n);

        // return s2_doWithCache(n);

        return s3_doWithCache(n);
    }

    // 最简单的方式,递归,实际上性能非常之差,运行超时
    private int simpleSolution(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return (fib(n - 1) + fib(n - 2)) % 1000000007;
        }
    }

    // 执行耗时:1 ms,击败了5.08% 的Java用户
    // 内存消耗:35.4 MB,击败了57.95% 的Java用户
    private int s2_doWithCache(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();
        cache.put(0, 0);
        cache.put(1, 1);

        for (int i = 2; i <= n; i++) {
            if (!cache.containsKey(i)) {
                cache.put(i, (cache.get(i - 1) + cache.get(i - 2)) % 1000000007);
            }

        }
        return cache.get(n);
    }

    // 解决方案二采用了缓存中间数据的方式，如果在需要进行多次计算的情况下非常有用，但如果只是计算一次，则很多中间结果是不需要缓存的
    // 只需要缓存前两次的结果就好，由此引申出第三种解决方案
    private int s3_doWithCache(int n) {
        int c1 = 0, c2 = 1, sum = 0;

        if (n == 0) {
            return c1;
        }
        if (n == 1) {
            return c2;
        }

        for (int i = 2; i <= n; i++) {
            sum = (c1 + c2) % 1000000007;
            c1 = c2;
            c2 = sum;
        }

        return sum;
    }
}
// leetcode submit region end(Prohibit modification and deletion)
