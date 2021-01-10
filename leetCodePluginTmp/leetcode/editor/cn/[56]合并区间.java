// 给出一个区间的集合，请合并所有重叠的区间。
//
//
//
// 示例 1:
//
// 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
// 输出: [[1,6],[8,10],[15,18]]
// 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
//
//
// 示例 2:
//
// 输入: intervals = [[1,4],[4,5]]
// 输出: [[1,5]]
// 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
//
// 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。
//
//
//
// 提示：
//
//
// intervals[i][0] <= intervals[i][1]
//
// Related Topics 排序 数组
// 👍 719 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

// leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public class Pair implements Comparable<Pair>{
        public int left;
        public int right;

        public Pair(int left, int right){
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Pair o) {
            return this.left-o.left;
        }
    }

    // 本题相当于取交集，按起始排序再合并
    public int[][] merge(int[][] intervals) {
        return solution2(intervals);
    }

    public int[][] solution3(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    //不借助Pair
    //解答成功:
    //			执行耗时:54 ms,击败了5.06% 的Java用户
    //			内存消耗:40.8 MB,击败了95.29% 的Java用户
    private int[][] solution2(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        Arrays.sort(intervals, (o1, o2)->{return o1[0]-o2[0];});
//       Arrays.sort(intervals, (int[] o1, int[] o2)->{return o1[0] - o2[0];});


        int lastRangeIndex  = 0; //当前数组中最后一个有效的独立区间，初始化为0
        for (int i = 1; i < intervals.length; i++) {
            //当前起点比上一个区间的终点还大，则新增加一个独立区间
            if (intervals[i][0] > intervals[lastRangeIndex][1])
            {
                ++lastRangeIndex;
                intervals[lastRangeIndex][0] = intervals[i][0];
                intervals[lastRangeIndex][1] = intervals[i][1];
            }
            else{
                //起始比上一个区间起点还小，刷新区间左边界
                if (intervals[i][0]<intervals[lastRangeIndex][0])
                {
                    intervals[lastRangeIndex][0] = intervals[i][0];
                }
                //当前区间右边界扩大
                if (intervals[i][1]>intervals[lastRangeIndex][1]){
                    intervals[lastRangeIndex][1] = intervals[i][1];
                }
            }
        }

        return Arrays.copyOf(intervals, lastRangeIndex+1);
    }

    //解答成功:
    //			执行耗时:54 ms,击败了5.06% 的Java用户
    //			内存消耗:41.1 MB,击败了76.41% 的Java用户
    private int[][] solution1(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        sort(intervals);

        ArrayList<Pair> lst = new ArrayList<>();

        lst.add(new Pair(intervals[0][0], intervals[0][1]));
        for (int i = 1; i < intervals.length; i++) {
            Pair lastPair = lst.get(lst.size()-1);

            if (intervals[i][0] > lastPair.right)
            {
                lst.add(new Pair(intervals[i][0], intervals[i][1]));
            }
            else{
                if (intervals[i][0]<lastPair.left)
                {
                    lastPair.left = intervals[i][0];
                }
                if (intervals[i][1]>lastPair.right){
                    lastPair.right = intervals[i][1];
                }
            }
        }

        int[][] result = new int[lst.size()][2];
        for (int i = 0; i < lst.size(); i++) {
            result[i][0] = lst.get(i).left;
            result[i][1] = lst.get(i).right;
        }

        return result;
    }

    public void sort(int[][] intervals) {

//        Arrays.sort(intervals, new Comparator<int[]>() {
//            public int compare(int[] interval1, int[] interval2) {
//                return interval1[0] - interval2[0];
//            }
//        });

        int[] tmp = new int[2];
        for (int i = 0; i < intervals.length-1; i++) {
            int j = i + 1;
            tmp[0] = intervals[j][0];
            tmp[1] = intervals[j][1];

            while (j > 0 && tmp[0] < intervals[j - 1][0]) {
                intervals[j][0] = intervals[j - 1][0];
                intervals[j][1] = intervals[j - 1][1];
                j--;
            }

            intervals[j][0] = tmp[0];
            intervals[j][1] = tmp[1];
        }
    }
}
// leetcode submit region end(Prohibit modification and deletion)
