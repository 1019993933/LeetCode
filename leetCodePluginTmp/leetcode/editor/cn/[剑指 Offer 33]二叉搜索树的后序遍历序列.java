// 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
//
//
//
// 参考以下这颗二叉搜索树：
//
// 5
// / \
// 2 6
// / \
// 1 3
//
// 示例 1：
//
// 输入: [1,6,3,2,5]
// 输出: false
//
// 示例 2：
//
// 输入: [1,3,2,6,5]
// 输出: true
//
//
//
// 提示：
//
//
// 数组长度 <= 1000
//
// 👍 144 👎 0

import java.util.Stack;

// leetcode submit region begin(Prohibit modification and deletion)
/*
 * /解题思路：没有重复数字，不用考虑相等情况 1， 因为是后序，所以根节点在最后，如果存在右子节点，则与右子节点必定连续即有 num[i-1]>num[i]，
 * 即对于一个连续的[i,j],如果存在num[k]>=num[k+1],则从i开始的节点必定是其后节点的右子节点 2， 对于第一个出现的
 * num[i]<num[i+1]，从第一条可得出节点i必定是左节点，且i+1节点之下再也没有右节点，则i的位置需要确定，过程如下： 针对num[i]<num[i+1]，
 * i+1必定是以下两种情况这一：根节点或者存在num[i+1]>num[i+2]（因为是第一次出现的破坏递增的情况） 则 i
 * 可以为i+1的左节点，也可以为i+1父节点或以上节点的子节点，假设其父节点为k,(k>=i+1)，则存在如下关系 num[i]<num[k], num[i]>num[k+1], 因为k+1为k的根节点，则k下面的所有节点都会比k大
 * 所以对于第一个出现的 num[i]<num[i+1]，只需要在后续的节点打到这个k即可 3， 根据第二步设num[i]为左节点，如果num[i-1]>num[i],则i+1为i的右子节点，重复1~2步骤找下一个节点
 * 如果num[i-1]<num[i],则依然是找出k,满足 num[i-1]<num[k], num[i-1]>num[k+1] （k>=i+1) 以上k为根节点则不用后面的条件判断
 * 
 * 如果找不到这样的k,则不满足
 * 
 * 3： 根据以上分析，实现步骤为逆序遍历后续遍历结果，采用Stack缓存满足递增关系的连接节点，对破坏递增关系的第一个节点i，出栈找到一个合适位置 满足递增将i加入Stack，再判断下一个节点
 * 
 * 初始化Stakc为根节点A，其值等于根节点root值， 对于首次出的节点i为root的左节点情况，会将root删除， 找到对应的k，并应k以前的缓存清除，构建新缓存 [i, k+1~n-1]，如果k为根节点则缓存为[i]
 */
class Solution {

    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null || postorder.length <= 1) {
            return true;
        }

        Stack<Integer> stack = new Stack<>();

        int popValue = Integer.MAX_VALUE;
        for (int i = postorder.length - 1; i >= 0; i--) {
            int curValue = postorder[i];

            if (curValue>popValue)
            {
                return false;
            }

            while (!stack.isEmpty() && curValue < stack.peek()) {
                popValue = stack.pop();
            }

            stack.push(curValue);
        }

        return true;
    }
}
// leetcode submit region end(Prohibit modification and deletion)
