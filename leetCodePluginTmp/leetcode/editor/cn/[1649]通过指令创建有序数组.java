//给你一个整数数组 instructions ，你需要根据 instructions 中的元素创建一个有序数组。一开始你有一个空的数组 nums
// ，你需要 从
//左到右 遍历 instructions 中的元素，将它们依次插入 nums 数组中。每一次插入操作的 代价 是以下两者的 较小值 ： 
//
// 
// nums 中 严格小于 instructions[i] 的数字数目。 
// nums 中 严格大于 instructions[i] 的数字数目。 
// 
//
// 比方说，如果要将 3 插入到 nums = [1,2,3,5] ，那么插入操作的 代价 为 min(2, 1) (元素 1 和 2 小于 3 ，元素 5 
//大于 3 ），插入后 nums 变成 [1,2,3,3,5] 。 
//
// 请你返回将 instructions 中所有元素依次插入 nums 后的 总最小代价 。由于答案会很大，请将它对 109 + 7 取余 后返回。 
//
// 
//
// 示例 1： 
//
// 输入：instructions = [1,5,6,2]
//输出：1
//解释：一开始 nums = [] 。
//插入 1 ，代价为 min(0, 0) = 0 ，现在 nums = [1] 。
//插入 5 ，代价为 min(1, 0) = 0 ，现在 nums = [1,5] 。
//插入 6 ，代价为 min(2, 0) = 0 ，现在 nums = [1,5,6] 。
//插入 2 ，代价为 min(1, 2) = 1 ，现在 nums = [1,2,5,6] 。
//总代价为 0 + 0 + 0 + 1 = 1 。 
//
// 示例 2: 
//
// 输入：instructions = [1,2,3,6,5,4]
//输出：3
//解释：一开始 nums = [] 。
//插入 1 ，代价为 min(0, 0) = 0 ，现在 nums = [1] 。
//插入 2 ，代价为 min(1, 0) = 0 ，现在 nums = [1,2] 。
//插入 3 ，代价为 min(2, 0) = 0 ，现在 nums = [1,2,3] 。
//插入 6 ，代价为 min(3, 0) = 0 ，现在 nums = [1,2,3,6] 。
//插入 5 ，代价为 min(3, 1) = 1 ，现在 nums = [1,2,3,5,6] 。
//插入 4 ，代价为 min(3, 2) = 2 ，现在 nums = [1,2,3,4,5,6] 。
//总代价为 0 + 0 + 0 + 0 + 1 + 2 = 3 。
// 
//
// 示例 3： 
//
// 输入：instructions = [1,3,3,3,2,4,2,1,2]
//输出：4
//解释：一开始 nums = [] 。
//插入 1 ，代价为 min(0, 0) = 0 ，现在 nums = [1] 。
//插入 3 ，代价为 min(1, 0) = 0 ，现在 nums = [1,3] 。
//插入 3 ，代价为 min(1, 0) = 0 ，现在 nums = [1,3,3] 。
//插入 3 ，代价为 min(1, 0) = 0 ，现在 nums = [1,3,3,3] 。
//插入 2 ，代价为 min(1, 3) = 1 ，现在 nums = [1,2,3,3,3] 。
//插入 4 ，代价为 min(5, 0) = 0 ，现在 nums = [1,2,3,3,3,4] 。
//​​​​​插入 2 ，代价为 min(1, 4) = 1 ，现在 nums = [1,2,2,3,3,3,4] 。
//插入 1 ，代价为 min(0, 6) = 0 ，现在 nums = [1,1,2,2,3,3,3,4] 。
//插入 2 ，代价为 min(2, 4) = 2 ，现在 nums = [1,1,2,2,2,3,3,3,4] 。
//总代价为 0 + 0 + 0 + 0 + 1 + 0 + 1 + 0 + 2 = 4 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= instructions.length <= 105 
// 1 <= instructions[i] <= 105 
// 
// Related Topics 树状数组 线段树 Ordered Map 
// 👍 17 👎 0


import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private long MOD_CONST = 100007;

    public int createSortedArray(int[] instructions) {
        if (instructions==null || instructions.length<=1){
            return 0;
        }

        //离散化，此处需要利用到序号的关系来判断元素值的大小关系，所以需要排序，利用Treeset, 如果没有排序要求，可以用HashSet
        Set<Integer> set = new TreeSet<>();
        for(int x: instructions){
            set.add(x);
        }

        //哈希表用来存储离散化后序号与原值的对应关系
        HashMap<Integer, Integer> map = new HashMap<>();
        int idx = 0;
        for (Integer x: set){
            map.put(x, ++idx);
        }

        long totalCost = 0;

        //初始化长度其实是比较随意的，只需要保证比离散化后的空间多2个即可，一个是序号0作哨兵使用（树状数组的计算逻辑是依赖于从1开始的序号）
        // 一个是保存原数组前n个元素的和，即对应n+1个元素的前缀和
        FenwickTree tree = new FenwickTree(map.size()+1);

        //对原数组遍历，由于原题意是计算当前待插入值的代价，隐含意思就是对每一个待计算的元素，只需要考虑之前已经处理过的即可，其代价是
        //一个固定值，且只依赖于之前处理的元素，所以在处理第i个元素时，树状数组中存储中是仅仅是前i-1个元素的信息
        for (int i = 0; i < instructions.length; i++) {
            idx = map.get(instructions[i]); //map中获取数组元素对应离散化后的序号
            int treeIndex = idx + 1; //由于树状数组的计算特性，其序号要相对原始序号加1

            //这条语句的意思是计算treeIndex对应的前缀和，也就是原数组第i(treeIndex-1)个元素的前缀和
            //即前i-1个元素中小于元素 instructions[i]的个数
            long leftCost = tree.sum(treeIndex);

            //当计算比instructions[i]大的元素个数时，没有办法直接获取，需要利用一次减法操作
            // 设当前值为key, 对应的树状数组序号为 i, 树状数组的初始化最大长度为size, 此处的size>=n+2(n为原离散化后的区间元素个数)
            // 则比key值小的个数为 tree.sum(i)
            // 小于等于key值的个数为 tree.sum(i+1)
            // 总的元素个数为 tree.sum(size)
            // 大于key值的个数为  总个数 - 小于等于的个数， 即 tree.sun(size) - tree.sum(i+1)
            long rightCost = tree.sum(map.size()+2)  - tree.sum(treeIndex+1);

            totalCost += (leftCost>rightCost)? rightCost: leftCost;
            totalCost %= MOD_CONST;

            //处理完当前元素，将该元素放入树状数组中，用于计算下一个元素
            //已知 treeIndex对应的比当前元素值小的元素个数，所以加入该元素的逻辑是使比key值大的所有元素数值+1，即
            //比treeIndex大的元素需要+1
            tree.update(treeIndex+1, 1);
        }

        return (int)(totalCost % MOD_CONST) ;
    }

    public class FenwickTree{
        private int n;
        private int[] tree;

        public FenwickTree(int n){
            this.n = n + 5;  //树状数组做为一个统计前缀各的工具，后面多增加几个元素空间并没有什么影响
            tree = new int[this.n];
        }

        private int lowbit(int x){
            return x & (-x);
        }

        public void update(int i, int delt){ //delt是对应位置的元素增量，如果是求权值，此处为原数组值，如果只是求数量，此处其实传入的是1
            while(i<n){
                tree[i] += delt;
                i += lowbit(i);
            }
        }

        public int sum(int i){
            int sum = 0;
            while(i>0){  //由于树状数组的计算逻辑是从下标1开始的，所以0是一个无效值，做为哨兵使用，没有实际意义
                sum += tree[i];
                i -= lowbit(i);
            }
            return sum;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
