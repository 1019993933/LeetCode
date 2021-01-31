//给你一个 m * n 的整数矩阵 mat ，请你将同一条对角线上的元素（从左上到右下）按升序排序后，返回排好序的矩阵。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
//输出：[[1,1,1,1],[1,2,2,2],[1,2,3,3]]
// 
//
// 
//
// 提示： 
//
// 
// m == mat.length 
// n == mat[i].length 
// 1 <= m, n <= 100 
// 1 <= mat[i][j] <= 100 
// 
// Related Topics 排序 数组 
// 👍 30 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] diagonalSort(int[][] mat) {
        //一共有m+n-1条对角线，对象线坐标从起始坐标 x+1, y-1
        int m = mat.length;
        int n = mat[0].length;

        Point endPoint  = new Point(m-1, n-1);
        for (int i = 0; i < n; i++) {
            Point startPoint = new Point(0, i);
            sort(mat, startPoint, endPoint);
        }

        for (int i = 0; i < m; i++) {
            Point startPoint = new Point(i, 0);
            sort(mat, startPoint, endPoint);
        }

        return mat;
    }

    private void sort(int[][] mat, Point startPoint, Point endPoint){
        for (int x = startPoint.x, y=startPoint.y; x <= endPoint.x && y<=endPoint.y; x++, y++) {
            Point minPoint = new Point(x, y);
            for (int x1=x, y1=y;x1 <= endPoint.x && y1<=endPoint.y; x1++, y1++) {
                if (mat[x1][y1]<mat[minPoint.x][minPoint.y]){
                    minPoint.x = x1;
                    minPoint.y = y1;
                }
            }
            swap(mat, x, y, minPoint.x, minPoint.y);
        }
    }

    private void swap(int[][] mat, int x, int y, int x1, int y1) {
        int tmp = mat[x][y];
        mat[x][y] = mat[x1][y1];
        mat[x1][y1] = tmp;
    }

    public class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
