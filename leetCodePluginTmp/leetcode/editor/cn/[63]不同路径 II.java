//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。 
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。 
//
// 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？ 
//
// 
//
// 网格中的障碍物和空位置分别用 1 和 0 来表示。 
//
// 
//
// 示例 1： 
//
// 
//输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
//输出：2
//解释：
//3x3 网格的正中间有一个障碍物。
//从左上角到右下角一共有 2 条不同的路径：
//1. 向右 -> 向右 -> 向下 -> 向下
//2. 向下 -> 向下 -> 向右 -> 向右
// 
//
// 示例 2： 
//
// 
//输入：obstacleGrid = [[0,1],[0,0]]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// m == obstacleGrid.length 
// n == obstacleGrid[i].length 
// 1 <= m, n <= 100 
// obstacleGrid[i][j] 为 0 或 1 
// 
// Related Topics 数组 动态规划 
// 👍 450 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int [][] d = new int[m][n];

        d[0][0] = (obstacleGrid[0][0]==1)? Integer.MIN_VALUE: 1;
        for (int i = 1; i < m; i++) {
            d[i][0] = (d[i-1][0]==Integer.MIN_VALUE || obstacleGrid[i][0]==1)? Integer.MIN_VALUE: 1;
        }
        for (int i = 1; i < n; i++) {
            d[0][i] = (d[0][i-1]==Integer.MIN_VALUE || obstacleGrid[0][i]==1)? Integer.MIN_VALUE: 1;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j]==1){
                    d[i][j] = Integer.MIN_VALUE;
                }
                else{
                    if (d[i][j] == 0){
                        if (d[i-1][j]==Integer.MIN_VALUE && d[i][j-1]==Integer.MIN_VALUE)
                        {
                            d[i][j] = Integer.MIN_VALUE;
                        }
                        else if (d[i-1][j]==Integer.MIN_VALUE)
                        {
                            d[i][j] = d[i][j-1];
                        }
                        else if (d[i][j-1]==Integer.MIN_VALUE)
                        {
                            d[i][j] = d[i-1][j];
                        }
                        else
                        {
                            d[i][j] = d[i-1][j] + d[i][j-1];
                        }
                    }
                }
            }
        }


        return d[m-1][n-1]==Integer.MIN_VALUE? 0: d[m-1][n-1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
