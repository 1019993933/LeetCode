//给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。 
//
// 只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。 
//
// 在完成所有删除操作后，请你返回列表中剩余区间的数目。 
//
// 
//
// 示例： 
//
// 
//输入：intervals = [[1,4],[3,6],[2,8]]
//输出：2
//解释：区间 [3,6] 被区间 [2,8] 覆盖，所以它被删除了。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 1000 
// 0 <= intervals[i][0] < intervals[i][1] <= 10^5 
// 对于所有的 i != j：intervals[i] != intervals[j] 
// 
// Related Topics 贪心算法 排序 Line Sweep 
// 👍 34 👎 0


import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    TreeSet<Node> ranges = new TreeSet<>();

    public int removeCoveredIntervals(int[][] intervals) {
        for (int[] interval : intervals) {
            book(interval[0], interval[1]);
        }

        return ranges.size();
    }

    private void book(int left, int right){
        Iterator<Node> ite = ranges.tailSet(new Node(0, left+1)).iterator();
        while (ite.hasNext()){
            Node node = ite.next();
            if (node.left>=right){
                break;
            }
            if (node.left<=left && node.right>=right){
                return;
            }
            else if (node.left>=left && node.right<=right){
                ite.remove();
            }
        }

        ranges.add(new Node(left, right));
    }

    public class Node implements Comparable<Node>{
        int left;
        int right;

        public Node(int left, int right){
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node o) {
            return (this.right==o.right)? this.left-o.left: this.right-o.right;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
