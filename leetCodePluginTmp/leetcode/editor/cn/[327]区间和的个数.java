// 给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
// 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
//
// 说明:
// 最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。
//
// 示例:
//
// 输入: nums = [-2,5,-1], lower = -2, upper = 2,
// 输出: 3
// 解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。
//
// Related Topics 排序 树状数组 线段树 二分查找 分治算法
// 👍 278 👎 0

import java.util.HashMap;
import java.util.HashSet;

// leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private int count;


    // 此题的所有解法都依赖于排序后的前缀和
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums==null || nums.length==0){
            return 0;
        }

        return solutionWithFenwickTree(nums, lower, upper);
    }

    // 采用归并排序方法解决，注意此时并不是对原数组归并排序，对是对前缀各进行归并排序
    // 此处需要注意的是排序后的结果本身是不可利用的，因为其顺序已被排序破坏，我们此处利用的是排序过程中前后两次分区进顺序性，即在分组过程中
    // 后一组在顺序上总是在前一组之后的，但是合并后其顺序就已经打乱了

    // 对未排序的[0, len-1]分组为[0, k], [k+1, len], 对任意i属于【0， k], j属于[k+1， len], [i，j]总是构成一个连续的区间
    // 但是对于已经排序完成的[0, len-1]， 其中下标已经不具任何意义

    // 也就是说，对于sum[]数组，在未排序前，其中的下标即对应原数组前i对应的前缀和，但是排序完成后，下标就没法对应了

    // 类似可以利用此算法的是求逆数对

    // sum[i]表示前i个元素的各，我们要找的就是 sum[j]-sum[i]在区间[lower, upper] 内，此时[i,j]即为符合要求的区间对
    // 这个计数过程可以在归并排序的过程中，对结果进行统计
    public int solution1(int[] nums, int lower, int upper) {
        long s = 0;
        long[] sum = new long[nums.length+1];
        for (int i = 0; i < nums.length; i++) {
            sum[i+1] += (nums[i] + sum[i]);
        }

        mergeSort(sum, 0, sum.length - 1, lower, upper);

        return count;
    }

    private void mergeSort(long[] nums, int start, int end, int lower, int upper){
        if (start==end){
//            if (nums[start]>=lower && nums[start]<=upper){
//                ++count;
//            }
            return;
        }

        int mid = (end+start)/2;
        mergeSort(nums, start, mid, lower, upper);
        mergeSort(nums, mid+1, end, lower, upper);

        merge(nums, start, mid, end, lower, upper);
    }

    //合并的过程即包含了计数的过程
    private void merge(long[] nums, int start, int mid, int end, int low, int upper){
        int i = start;
        int l = mid + 1;
        int r = mid + 1;
        while (i <= mid) {
            while (l <= end && nums[l] - nums[i] < low) {
                l++;
            }
            while (r <= end && nums[r] - nums[i] <= upper) {
                r++;
            }

            count += (r-l);
            ++i;
        }

        long [] sorted = new long[end-start+1];
        int p1 = start;
        int p2 = mid + 1;
        int p = 0;
        while(p1<=mid || p2<=end)
        {
            if (p1>mid){
                sorted[p++] = nums[p2++];
            }
            else if (p2>end){
                sorted[p++] = nums[p1++];
            }
            else {
                sorted[p++] = (nums[p1]>nums[p2]? nums[p2++]: nums[p1++]);
            }
        }

        System.arraycopy(sorted, 0, nums, start, sorted.length);
    }


    //方法二： 采用树状数组
    public int solutionWithFenwickTree(int[] nums, int lower, int upper){
        long[] sum = new long[nums.length+1];
        for (int i = 0; i < nums.length; i++) {
            sum[i+1] += sum[i] + nums[i];
        }

        //对sum进行离散化处理, 通过set可以记录需要多少个下标
        HashSet<Long> set = new HashSet<>();
        for (long num: sum){
            set.add(num);
            set.add(num-lower);
            set.add(num-upper);
        }

        HashMap<Long, Integer> values = new HashMap<>();
        int idx = 0;
        for (long x: set){
            values.put(x, idx++); //每次放一个数后序号加1，这样就将原来的值空间映射成了1~n的下标
        }

        int count = 0;
        FenwickTree fenwickTree = new FenwickTree(values.size());
        for (int i = 0; i < sum.length; i++) {
            int left = values.get(sum[i] - upper); //left对应的下标，对应set中相应的key值，及用该key值在map中保存的序号
            int right = values.get(sum[i] - lower);
            count += fenwickTree.query(right+1) - fenwickTree.query(left);
            //把第i个前缀和添加到树中，即其对应的序号+1对应的下标值，更新次数增1
            //序号加1是因为序号0保存的次数为0，
            fenwickTree.update(values.get(sum[i])+1, 1);


        }

        return count;
    }

    public class FenwickTree{
        private int[] tree;
        private int n;

        public FenwickTree(int n){
            this.n = n;
            this.tree = new int[n+1];
        }

        public void update(int i, int val){
            while(i<=n){
                tree[i] += val;
                i += lowbit(i);
            }
        }

        public int query(int i){
            int ans = 0;
            while(i>0){ //0号位置存储的是零，对应0个元素的和，Fenwick数组从1到n满足 lowbit关系
                ans += tree[i];
                i -= lowbit(i);
            }
            return ans;
        }

        private int lowbit(int x){
            return (x&(-x));
        }
    }
}
// leetcode submit region end(Prohibit modification and deletion)
