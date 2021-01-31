package com.study.leetcode.solutions;//Range 模块是跟踪数字范围的模块。你的任务是以一种有效的方式设计和实现以下接口。

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

//
// 
// addRange(int left, int right) 添加半开区间 [left, right)，跟踪该区间中的每个实数。添加与当前跟踪的数字部分重叠
//的区间时，应当添加在区间 [left, right) 中尚未跟踪的任何数字到该区间中。 
// queryRange(int left, int right) 只有在当前正在跟踪区间 [left, right) 中的每一个实数时，才返回 true。 
//
// removeRange(int left, int right) 停止跟踪区间 [left, right) 中当前正在跟踪的每个实数。 
// 
//
// 
//
// 示例： 
//
// addRange(10, 20): null
//removeRange(14, 16): null
//queryRange(10, 14): true （区间 [10, 14) 中的每个数都正在被跟踪）
//queryRange(13, 15): false （未跟踪区间 [13, 15) 中像 14, 14.03, 14.17 这样的数字）
//queryRange(16, 17): true （尽管执行了删除操作，区间 [16, 17) 中的数字 16 仍然会被跟踪）
// 
//
// 
//
// 提示： 
//
// 
// 半开区间 [left, right) 表示所有满足 left <= x < right 的实数。 
// 对 addRange, queryRange, removeRange 的所有调用中 0 < left < right < 10^9。 
// 在单个测试用例中，对 addRange 的调用总数不超过 1000 次。 
// 在单个测试用例中，对 queryRange 的调用总数不超过 5000 次。 
// 在单个测试用例中，对 removeRange 的调用总数不超过 1000 次。 
// 
//
// 
// Related Topics 线段树 Ordered Map 
// 👍 69 👎 0
//leetcode submit region begin(Prohibit modification and deletion)
class LT715_2 {
    @Test
    public void test() {
        LT715_2 obj = new LT715_2();
        obj.addRange(10, 20);
        obj.removeRange(14, 16);
        boolean b = obj.queryRange(10, 14);
        boolean b1 = obj.queryRange(13, 15);
        boolean b2 = obj.queryRange(16, 17);
        System.out.println("fdas");
    }

    TreeSet<Range> ranges = new TreeSet<>();

    public LT715_2() {
    }

    public void addRange(int left, int right) {
        Iterator<Range> ite = ranges.tailSet(new Range(0, left - 1)).iterator();
        while (ite.hasNext()) {
            Range range = ite.next();
            if (range.left > right) break;
            left = Math.min(left, range.left);
            right = Math.max(right, range.right);
        }
        ranges.add(new Range(left, right));
    }

    public boolean queryRange(int left, int right) {
        Range range = ranges.higher(new Range(0, left));
        return (range != null && range.left <= left && range.right >= right);
    }

    public void removeRange(int left, int right) {
        Iterator<Range> ite = ranges.tailSet(new Range(0, left)).iterator();
        ArrayList<Range> lst = new ArrayList<>();
        while (ite.hasNext()) {
            Range range = ite.next();
            if (range.left > right) break;
            if (range.left < left) lst.add(new Range(range.left, left));
            if (range.right > right) lst.add(new Range(right, range.right));
        }
        for (Range range : lst) {
            ranges.add(range);
        }

    }

    class Range implements Comparable<Range> {
        private final int right;
        private final int left;

        public Range(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Range o) {
            if (this.right == o.right) return this.left - o.left;
            return this.right - o.right;
        }
    }
}
/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */
//leetcode submit region end(Prohibit modification and deletion)
