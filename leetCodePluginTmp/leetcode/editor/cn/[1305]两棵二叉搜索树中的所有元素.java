//给你 root1 和 root2 这两棵二叉搜索树。 
//
// 请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。. 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：root1 = [2,1,4], root2 = [1,0,3]
//输出：[0,1,1,2,3,4]
// 
//
// 示例 2： 
//
// 输入：root1 = [0,-10,10], root2 = [5,1,7,0,2]
//输出：[-10,0,0,1,2,5,7,10]
// 
//
// 示例 3： 
//
// 输入：root1 = [], root2 = [5,1,7,0,2]
//输出：[0,1,2,5,7]
// 
//
// 示例 4： 
//
// 输入：root1 = [0,-10,10], root2 = []
//输出：[-10,0,10]
// 
//
// 示例 5： 
//
// 
//
// 输入：root1 = [1,null,8], root2 = [8,1]
//输出：[1,1,8,8]
// 
//
// 
//
// 提示： 
//
// 
// 每棵树最多有 5000 个节点。 
// 每个节点的值在 [-10^5, 10^5] 之间。 
// 
// Related Topics 排序 树 
// 👍 50 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    ArrayList<Integer> ans1 = new ArrayList<>();
    ArrayList<Integer> ans2 = new ArrayList<>();

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        getAllElements(root1, ans1);
        getAllElements(root2, ans2);
        return getAllElements(ans1, ans2);
    }

    public static void getAllElements(TreeNode root, ArrayList<Integer> ans) {
        if (root == null) {
            return;
        }
        if (root.left == root.right) {
            ans.add(root.val);
            return;
        }

        getAllElements(root.left, ans);
        ans.add(root.val);
        getAllElements(root.right, ans);
    }

    public static List<Integer> getAllElements(ArrayList<Integer> lst1, ArrayList<Integer> lst2) {
        int i = 0;
        int j = 0;
        int k = 0;
        int m = lst1.size();
        int n = lst2.size();

        Integer[] ans1 = lst1.toArray(new Integer[0]);
        Integer[] ans2 = lst2.toArray(new Integer[0]);

        Integer[] ans = new Integer[m + n];
        while (i < m || j < n) {
            if (i<m && j<n){
                ans[k++] =(ans1[i]<ans2[j]? ans1[i++]: ans2[j++]);
            }else if (i<m){
                ans[k++] = ans1[i++];
            }else
            {
                ans[k++] = ans2[j++];
            }
        }

        return Arrays.asList(ans);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
