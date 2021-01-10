// 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是 nums[i] 右侧小于 num
// s[i] 的元素的数量。
//
//
//
// 示例：
//
// 输入：nums = [5,2,6,1]
// 输出：[2,1,1,0]
// 解释：
// 5 的右侧有 2 个更小的元素 (2 和 1)
// 2 的右侧仅有 1 个更小的元素 (1)
// 6 的右侧有 1 个更小的元素 (1)
// 1 的右侧有 0 个更小的元素
//
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 10^5
// -10^4 <= nums[i] <= 10^4
//
// Related Topics 排序 树状数组 线段树 二分查找 分治算法
// 👍 499 👎 0

import java.util.*;

// leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        if (nums==null || nums.length==0) {
            return new ArrayList<>();
        }
        if (nums.length==1)
        {
            ArrayList<Integer> lst = new ArrayList<>();
            lst.add(0);
            return lst;
        }

        // 采用快排，对每一个数，在其右侧数组中找到其快排的位置，有了该位置即可计算出左侧与右侧的值
        // 这个想法是错误的，首先快排过程中改变了原数组的前后关系，后面计算的都不是准确值
        // 其实，采用快排的思想时间复杂为O(n^2),同等于暴力解法


        //归并排序无法解决此问题，归并排序可以统计所有的逆序对数量，但是由于排序结果移动了元素位置，无法每个位置对应的逆序数量
        //===============

        //树状数组的通用公共为  a*f(j)+b < f(i) < c*f(j)+d, 由于在循环过程对每一个j,统计的是j之前的满足关系的i的数量，
        // 即满足关系的左边的数量，按此题意只需要将原数组先逆序即可
        // 或者从后往前遍历

        // 解法一：原始暴力解法
//        return solution1(nums);

        // 解溉二：树状数组
        return  solutionWithFenwickTree(nums);
    }

    //==============解法一，作为解题思路
    // 先复制一份数组，原复制后的数组排序，然后去重，再对去重后的数组，定义一个相应长度的数组（桶）用来存储对应元素的个数
    // 桶的数组下标对应着原来元素的值，这样桶的顺序关系即反应了原数组的排序关系
    // 桶的值初始化为0
    // 然后再对原数组从后往前进行遍历， 此遍历顺序关系即保证了只有在该元素后面的数值才会出现在桶数组中，所以对桶当前数组的元素统计即反应了如下关系
    // 设当前查询的原数组下标i, 对k=nums[i]的桶下标,桶数组k之前的所有元素值求和，其对应的实际意义为 在原数组i之后的元素中小于k的元素个数，桶数组k之后的值求和，表示原数组i之后元素大于k的元素个素（仅在当下有效，下次更新后即丢失了这个信息）
    // 当处理完当前的k之后，将k对应的桶元素值 +1， 即表示这个元素放下桶中，以便下一个元素的统计可以使用
    private List<Integer> solution1(int[] nums) {
        //1： 原数组中的值对应桶的下标（假设原数组中没有负数），求原数组中最大值，这个就可以知道需要用多少个桶
        int maxValue = Arrays.stream(nums).max().getAsInt();
        int [] bucket = new int[maxValue+1]; //桶初始化完成后，对原数组任一元素nums[i], bucket[nums[i]]即表示该元素的个数，此处的思想可以参考计数排序

        // 存放结果的数组
        Integer[] count = new Integer[nums.length];
        for (int i = nums.length-1; i >= 0; i--) {
            int curCount = 0;

            //对桶该下标前面的元素求和，即为小于i位置的元素数量（这些元素都是在i之后的，第一重循环保证了这一点）
            for (int j = 0; j < nums[i]; j++) {
                curCount += bucket[j];
            }
            count[i] = curCount;

            //将当前的值加到桶中
            bucket[nums[i]] += 1;
        }

        return Arrays.asList(count);
    }

    //---------------方法一的演进
    private List<Integer> solution2(int[] nums) {
        //实际上，我们不同需要0到maxValue的空间做桶，因为其中可能有大量的无效桶，同时如果有负数的情况，此处也不能直接对应下标
        //优化的方法即为对原数组的元素进行离散化，离散化的意思就是将原数值的值空间映射到一个没有重复的[0~n]的连续空间
        //一种处理方法为通过TreeSet进行去重排序
        int maxValue = Arrays.stream(nums).max().getAsInt();
        int [] bucket = new int[maxValue+1];

        //再利用Hashmap存储TreeSet中的元素与其下标的对应关系


        Integer[] count = new Integer[nums.length];

        //在计算某个桶的前面的元素数量时，一种应对大量数据的更加有效的数据结果就是 树状数组
        //在树状树组中每一个大于0的元素值，其含义为其前面的所有元素的和，这样就可以使用树状数组get(nums[i])来代替下面的循环

        //下面这个循环，其最终的时间复杂度为O(n^2)，其实际上就是对每一个下标k，求k之前的所有元素的和，解决此问题的一个有效的数据结构即为树状数组
        //对应树状数组的查询操作与更新操作，时间复杂度均为O(logN),最终复杂度为O(NlogN)
        for (int i = nums.length-1; i >= 0; i--) {
            int curCount = 0;

            for (int j = 0; j < nums[i]; j++) {
                curCount += bucket[j];
            }
            count[i] = curCount;

            //更新操作，也可以使用树状数组的update方法来更新，
            bucket[nums[i]] += 1;
        }

        return Arrays.asList(count);
    }

    //===============方法二，利用树状数组
    private List<Integer> solutionWithFenwickTree(int[] nums) {
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        HashMap<Integer, Integer> values = new HashMap<>();
        int idx = 0;
        for(int x: set){
            values.put(x, idx++);
        }

        BitTree tree = new BitTree(values.size()+1);
        List<Integer> lst = new ArrayList<>();

        Integer[] count = new Integer[nums.length];

        int left = 0;
        for (int i = nums.length-1; i >= 0; i--) {
            // 原始序号与树状数组序号存在差1的关系，即原始序号0对应树状数组序号1
            // 对树状数组序号1取值，其含义为树数组（第一个元素桶序号0）的前缀和即为tree[1]={tree[0]+...+tree[1-1]} = tree[0] = 0(第一个元素前面没有元素，其前面的和自然为0)
            // 树状数组序号为 nums.length，对应桶数组最后一个元素nums.length-1的前缀和
            idx = values.get(nums[i]);
            int treeIdx =  idx+1;
            count[i] = tree.query(treeIdx);
            tree.update(treeIdx+1, 1);
        }

        return Arrays.asList(count);
    }

    public class BitTree{
        private int[] tree;
        private int n;

        public BitTree(int n){
            this.n = n+1;
            this.tree = new int[this.n];
        }

        public void update(int i, int val){
            while(i<n){
                tree[i] += val;
                i += lowbit(i);
            }
        }

        public int query(int i){
            int ans = 0;
            while(i>0){
                ans += tree[i];
                i -= lowbit(i);
            }

            return ans;
        }

        private int lowbit(int x){
            return (x & (-x));
        }
    }


}
// leetcode submit region end(Prohibit modification and deletion)
