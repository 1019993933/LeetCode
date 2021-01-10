//给出一个无重叠的 ，按照区间起始端点排序的区间列表。 
//
// 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。 
//
// 
//
// 示例 1： 
//
// 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
//输出：[[1,5],[6,9]]
// 
//
// 示例 2： 
//
// 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
//输出：[[1,2],[3,10],[12,16]]
//解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
// 
//
// 
//
// 注意：输入类型已在 2019 年 4 月 15 日更改。请重置为默认代码定义以获取新的方法签名。 
// Related Topics 排序 数组 
// 👍 339 👎 0


import java.lang.reflect.Array;
import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals ==null || intervals.length == 0) {
            int[][] res = new int[1][2];
            res[0] = newInterval;
            return res;
        }
        if(newInterval==null || newInterval.length==0){
            return intervals;
        }

        //Arrays.sort(intervals, (o1, o2)->{return o1[0]-o2[0];});

        return solution1(intervals, newInterval);
    }

    private int[][] solution1(int[][] intervals, int[] newInterval) {
        Deque<int[]> lst = new LinkedList<>();
        lst.add(newInterval);

        boolean finished = false;
        for (int i = 0; i< intervals.length; i++) {
            //已经处理完了，后面的依次加到列表中
            if (finished || rightRange(intervals[i], lst.getLast()))
            {
                lst.add(intervals[i]);
                finished = true;
                continue;
            }

            //待比较的区间在左边
            if (leftRange(intervals[i], lst.getLast()))
            {
                int[] last = lst.removeLast();
                lst.add(intervals[i]);
                lst.add(last);
            }
            else{
                int[] last = lst.getLast();
                last[0] = Math.min(intervals[i][0], last[0]);
                last[1] = Math.max(intervals[i][1], last[1]);
            }
        }
        return lst.toArray(new int[lst.size()][]);
    }

    public boolean outRange(int[] a, int []b)
    {
        return leftRange(a, b) || rightRange(a, b);
    }

    public boolean leftRange(int[] a, int [] b){
        return a[1]<b[0];
    }

    public boolean rightRange(int[] a, int[] b)
    {
        return leftRange(b, a);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
