//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。 
//
// 示例 1： 
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
// 
//
// 示例 2： 
//
// 输入: "cbbd"
//输出: "bb"
// 
// Related Topics 字符串 动态规划 
// 👍 2917 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /*状态方程为： DP[i, j] 为i到j的字符串
    //  DP[i,j]  = true                i==j即， 长度为1
    //           = Si==Sj?             j = i + 1, 长度为 2,判断两个字符是否相同
                 = DP[i+1, j-1] && Si==Sj        j > i + 1, 长度>2个，如果首尾不相同，则必须不是，如果相同，则去掉头尾比较

         此状态方程的难点在于与常规状态方程不同，i的计算依赖于 i+1, 需要通过控制循环来达到 dp[i+1, j-1]能够优先计算

         从二维表上来看，其长度为1对应于左上到右下的对角线
         计算方向为从该对角线往左下计算，依次计算平行与该对角线的线段上的值，该线段满足 j = i+1, 即可保证 dp[i+1,j-1]先于 dp[i,j]计算
     */
    public String longestPalindrome(String s) {
        return malanche(s);
    }

    private String dowithDP(String s) {
        int n = s.length();

        String ans = "";
        boolean [][] dp = new boolean [n][n]; //状态
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                if (i==j){
                    dp[i][j] = true;
                }
                else if (i+1==j)
                {
                    dp[i][j] = (s.charAt(i)== s.charAt(j));
                }
                else
                {
                    dp[i][j] = dp[i+1][j-1] && (s.charAt(i)== s.charAt(j));
                }

                if (dp[i][j] && j-i+1>ans.length())
                {
                    ans = s.substring(i, j+1);
                }
            }
        }

        return ans;
    }

    private String malanche(String s) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            builder.append("#").append(s.charAt(i));
        }
        builder.append("#");

        int m = builder.toString().length();
        int[] P = new int[m];

        int center = 0; //当前最大回文串中心坐标
        int R = 0;     //当前最大回文串最右字符坐标
        for (int i = 1; i < m; i++) {
            int j = 2*center - i;
            if (i < R)
            {
                //当前待计算的i在最大回文串中，则看其对称的坐标对应的len值,并与i到边界R的长度进行比较
                // 如果没有超出当前最大回文串长度，则其值即为len[j]
                // 如果超出，则对于R-i的部分肯定是满足回文的，但是要对i后面的字符再进行判断，所以有了下面的取小值
                P[i] = Math.min(P[j], R-i);
            }
            else
            {
                P[i] = 0;
            }

            //接下来，再对i的不确定部分进行回文验证，此条件可以针面上面的各种不满足情况进行刷新,包括以下三种情况
            //1: i<R,且P[i]>R-i的情况
            //2: P[i]==P[2*center-i]==0的
            //3:
            while(s.charAt(i+P[i] + 1)== s.charAt(i-P[i] - 1))
            {
                ++P[i];
            }

            if( i + P[i] > R)
            {
                center = i;
                R = i + P[i];
            }
        }

        //计算最大回文串
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < m-1; i++) {
            if (P[i]>maxLen){
                maxLen = P[i];
                centerIndex = i;
            }
        }

        return s.substring((centerIndex-maxLen)/2, maxLen);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
