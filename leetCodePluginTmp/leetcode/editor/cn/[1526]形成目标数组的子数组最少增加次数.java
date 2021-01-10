//给你一个整数数组 target 和一个数组 initial ，initial 数组与 target 数组有同样的维度，且一开始全部为 0 。 
//
// 请你返回从 initial 得到 target 的最少操作次数，每次操作需遵循以下规则： 
//
// 
// 在 initial 中选择 任意 子数组，并将子数组中每个元素增加 1 。 
// 
//
// 答案保证在 32 位有符号整数以内。 
//
// 
//
// 示例 1： 
//
// 输入：target = [1,2,3,2,1]
//输出：3
//解释：我们需要至少 3 次操作从 intial 数组得到 target 数组。
//[0,0,0,0,0] 将下标为 0 到 4 的元素（包含二者）加 1 。
//[1,1,1,1,1] 将下标为 1 到 3 的元素（包含二者）加 1 。
//[1,2,2,2,1] 将下表为 2 的元素增加 1 。
//[1,2,3,2,1] 得到了目标数组。
// 
//
// 示例 2： 
//
// 输入：target = [3,1,1,2]
//输出：4
//解释：(initial)[0,0,0,0] -> [1,1,1,1] -> [1,1,1,2] -> [2,1,1,2] -> [3,1,1,2] (tar
//get) 。
// 
//
// 示例 3： 
//
// 输入：target = [3,1,5,4,2]
//输出：7
//解释：(initial)[0,0,0,0,0] -> [1,1,1,1,1] -> [2,1,1,1,1] -> [3,1,1,1,1] 
//                                  -> [3,1,2,2,2] -> [3,1,3,3,2] -> [3,1,4,4,2]
// -> [3,1,5,4,2] (target)。
// 
//
// 示例 4： 
//
// 输入：target = [1,1,1,1]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= target.length <= 10^5 
// 1 <= target[i] <= 10^5 
// 
// Related Topics 线段树 
// 👍 23 👎 0


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minNumberOperations(int[] target) {
        int count = 0;
        SegmentTree tree = new SegmentTree(target);
        while(tree.getCurHeight()>0){
            count += tree.update();
        }

        return count;

    }

    public class TreeNode{
        int height;
        int left;
        int right;

        public TreeNode(int left, int right){
            this.left = left;
            this.right = right;
            this.height = height;
        }
    }

    public class SegmentTree{
        TreeNode[] tree;

        public SegmentTree(int[] target){
            tree = new TreeNode[target.length*4];
            buildTree(target, 1, 0, target.length-1);
        }

        public int getCurHeight(){
            return tree[1].height;
        }

        private void buildTree(int[] target, int i, int left, int right){
            tree[i] = new TreeNode(left, right);
            if (left==right){
                tree[i].height = target[left];
            }
            else{
                int mid = (left+right)>>1;
                buildTree(target, 2*i, left, mid);
                buildTree(target, 2*i+1, mid+1, right);
                tree[i].height = Math.max(tree[2*i].height, tree[2*i+1].height);
            }
        }

        private int update(){
            int count = 0;
            Set<Integer> set = new TreeSet<>();
            doUpdate(1, set);
            int[] result = set.stream().mapToInt(Integer::intValue).toArray();
            for (int i = 0; i < result.length-1; i++) {
                if (result[i]+1!=result[i+1]){
                    ++count;
                }
            }
            ++count;
            return count;
        }

        private void doUpdate(int i, Set<Integer> set){
            if (tree[i].height<=0){
                return;
            }

            tree[i].height -= 1;
            if (tree[i].left==tree[i].right){
                set.add(tree[i].left);
            }
            else{
                doUpdate(2*i, set);
                doUpdate(2*i+1, set);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
