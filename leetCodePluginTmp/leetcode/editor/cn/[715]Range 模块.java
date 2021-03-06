//Range 模块是跟踪数字范围的模块。你的任务是以一种有效的方式设计和实现以下接口。 
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
// 👍 70 👎 0

import com.sun.deploy.config.JREInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

//leetcode submit region begin(Prohibit modification and deletion)
class RangeModule {
    TreeSet<Range> set = new TreeSet<>();

    public RangeModule() {
    }

    public void addRange(int left, int right) {
        Iterator<Range> iterator = set.tailSet(new Range(0, left)).iterator();
        while(iterator.hasNext()){
            Range curRange = iterator.next();
            if (curRange.left>right){
                break;
            }
            left = Math.min(left, curRange.left);
            right = Math.max(right, curRange.right);
            iterator.remove();
        }
        set.add(new Range(left, right));
    }

    public boolean queryRange(int left, int right) {
        Range iv = set.higher(new Range(0, left));
        return (iv != null && iv.left <= left && right <= iv.right);
    }

    public void removeRange(int left, int right) {
        Iterator<Range> itr = set.tailSet(new Range(0, left)).iterator();
        ArrayList<Range> todo = new ArrayList();
        while (itr.hasNext()) {
            Range iv = itr.next();
            if (right < iv.left) break;
            if (iv.left < left) todo.add(new Range(iv.left, left));
            if (right < iv.right) todo.add(new Range(right, iv.right));
            itr.remove();
        }
        for (Range iv: todo) set.add(iv);
    }

    class Range implements Comparable<Range> {
        private int left;
        private int right;

        public Range(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Range that){
            if (this.right == that.right) return this.left - that.left;
            return this.right - that.right;
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
