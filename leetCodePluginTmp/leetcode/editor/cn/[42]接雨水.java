//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
// 
//
// 示例 2： 
//
// 
//输入：height = [4,2,0,3,2,5]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 0 <= n <= 3 * 104 
// 0 <= height[i] <= 105 
// 
// Related Topics 栈 数组 双指针 动态规划 
// 👍 1975 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public class Point{
        public int x;
        public int height;
        public int countHeight;
        public Point(int x, int height){
            this.x = x;
            this.height = height;
        }
    }
    public int trap(int[] height) {
        if (height==null || height.length<=2){
            return 0;
        }

        SolutionDP so = new SolutionDP();
        return so.trap(height);
    }

    /**
     * 暴力法，对于每个元素，其能容纳的水为其左右两边最大高度的较小值减去本身的高度
     * 		解答成功:
     * 		执行耗时:113 ms,击败了5.10% 的Java用户
     * 		内存消耗:37.9 MB,击败了79.90% 的Java用户
     */
    public class Sulution1{
        public int trap(int[] height) {
            int count = 0;
            for (int i = 0; i < height.length; i++) {
                int maxLeft = 0;
                int maxRight = 0;
                for (int j = 0; j < i; j++) {
                    if (height[j]>maxLeft){
                        maxLeft = height[j];
                    }
                }
                for (int j = i+1; j < height.length; j++) {
                    if (height[j]>maxRight){
                        maxRight = height[j];
                    }
                }

                if (Math.min(maxLeft, maxRight)>height[i]) {
                    count += (Math.min(maxLeft, maxRight) - height[i]);
                }
            }
            return count;
        }
    }

    /**
     * 动态规划算法是在暴力法的基础上，先找出每个元素对应的左右两边最大高度数组，再遍历计算结果， 在找最大高度时可以利用其一次的查找结果
     * 设 D[i] 为i节点左边最大高度，则D[i+1] = max（D[i], height[i+1], 右边同理
     * 		解答成功:
     * 		执行耗时:1 ms,击败了99.90% 的Java用户
     * 		内存消耗:38.4 MB,击败了5.02% 的Java用户
     */
    public class SolutionDP{
        public int trap(int[] height) {
            int size = height.length;
            int[] maxLeftHeight = new int[size];
            int[] maxRightHeight = new int[size];

            maxLeftHeight[0] = 0;
            maxRightHeight[size -1] =  0;
            for (int i = 1; i < size; i++) {
                maxLeftHeight[i] = Math.max(maxLeftHeight[i-1], height[i-1]);
                int j = size - i - 1;
                maxRightHeight[j] = Math.max(maxRightHeight[j+1], height[j+1]);
            }

            int count = 0;
            for (int i = 1; i < size-1; i++) {
                if (Math.min(maxLeftHeight[i], maxRightHeight[i])>height[i])
                {
                    count += (Math.min(maxLeftHeight[i], maxRightHeight[i]) - height[i]);
                }
            }

            return count;
        }

    }

    /**
     * 将数组高度连接线看成波浪线，由上升级段和下降线段组成，对下降线段入栈，对上升线段去栈中找对应高度元素，其距离构成可容纳的水的体积
     * 采用栈解决，遍历数组，如果当前元素比前一个高，则入栈，如果比前一个低，则去栈内找所有小于当前高度的点统计距离
     * 		解答成功:
     * 		执行耗时:4 ms,击败了29.20% 的Java用户
     * 		内存消耗:38 MB,击败了65.50% 的Java用户
     */
    public class SolutionWithStack{
        Stack<Point> stack = new Stack<>();

        public int trap(int[] height) {
            int count = 0;

            Point pre = new Point(0, height[0]);
            stack.push(pre);

            for (int i = 1; i < height.length; i++) {
                Point p = new Point(i, height[i]);
                if (p.height==0){
                    continue;
                }

                if (p.height<stack.peek().height){
                    Point topP = stack.peek();
                    count += ((p.x-topP.x-1)*p.height);
                    topP.countHeight = p.height;
                    stack.push(p);
                    continue;
                }

                while(!stack.isEmpty() && p.height>=stack.peek().height){
                    Point topP = stack.pop();
                    count += ((p.x-topP.x-1)*(topP.height-topP.countHeight));
                }
                if(stack.isEmpty()) {
                    stack.push(p);
                }else{
                    Point topP = stack.peek();
                    count += ((p.x-topP.x-1)*(p.height- topP.countHeight));
                    topP.countHeight = p.height;
                    stack.push(p);
                }
            }

            return count;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
