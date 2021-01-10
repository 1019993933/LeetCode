//给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。 
//
// 你需要返回给定数组中的重要翻转对的数量。 
//
// 示例 1: 
//
// 
//输入: [1,3,2,3,1]
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: [2,4,3,5,1]
//输出: 3
// 
//
// 注意: 
//
// 
// 给定数组的长度不会超过50000。 
// 输入数组中的所有数字都在32位整数的表示范围内。 
// 
// Related Topics 排序 树状数组 线段树 二分查找 分治算法 
// 👍 245 👎 0


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //目前会的两种解法，归并与树状数组
    public int reversePairs(int[] nums) {
        if (nums==null || nums.length<=1){
            return 0;
        }

        //info
        //		解答成功:
        //		执行耗时:61 ms,击败了34.11% 的Java用户
        //		内存消耗:48.2 MB,击败了45.15% 的Java用户
//        return solutionWithMerge(nums);


//        return solutionWithFenwickTree(nums);


        return solutionWithSegmentTree(nums);

    }

    public class Solution2 {
        public int solutionWithMerge(int[] nums) {
            return mergeSort(nums, 0, nums.length - 1);
        }

        private int mergeSort(int[] nums, int p, int q) {
            if (p == q) {
                return 0;
            }

            int mid = (p + q) / 2;
            int n1 = mergeSort(nums, p, mid);
            int n2 = mergeSort(nums, mid + 1, q);

            int n = merge(nums, p, mid, q);

            return n1 + n2 + n;
        }

        //在合并为有序数组前即对两个分组的数据进行判断，查询是否满足，之所以归并能够有效地解决此类问题，其原因在于
        //两个分组内部本身是有序的，可以充分利用有序性进行计算
        //即分别从两个数组取一个元素，其本身就保证了下标i<j,我们只需要判断 num[i]>2*num[j],而一旦满足，由于有序性，该次i之后的都满足

        private int merge(int[] nums, int p, int mid, int q) {
            int count = 0;

            int i = p;
            int j = mid + 1;
            while (i <= mid) //依次取前半个数组元素即num[i]
            {
                //第一次提交超时，此处由于递增性，j是不需要回退的
                //即，如果f(i)>2*f(j),则f(i+1)必然也会大于2*f(j)
                //即，对i,如果找到j使得 f(i)<=2*f(j)，此时对i的判断结束，取i+1来判断，此时只需要与j位置及之后的比较即可
//            int j = mid + 1;


                while (j <= q && (long) nums[i] > (long) 2 * nums[j]) {
                    ++j;
                }

                //j的位置不满足条件了，其后面的也肯定也不满足，此时的元素数量 为 （j-1) - (mid+1) + 1 = j - mid - 1
                count += (j - mid - 1);
                ++i;
            }

            //归并数组，为上层调用准备
            int[] sorted = new int[q - p + 1];
            int p1 = p;
            int p2 = mid + 1;
            int k = 0;
            while (p1 <= mid || p2 <= q) {
                if (p1 > mid) {
                    sorted[k++] = nums[p2++];
                } else if (p2 > q) {
                    sorted[k++] = nums[p1++];
                } else {
                    sorted[k++] = (nums[p1] > nums[p2] ? nums[p2++] : nums[p1++]);
                }
            }

            System.arraycopy(sorted, 0, nums, p, sorted.length);

            return count;
        }

    }

    //由题可知有 2f(j) < f(i) < maxValue, 对于给定的f(j), 求2f(j)到maxValue区间的数量
    public int solutionWithFenwickTree(int[] nums) {
        //离散化？？需要吗，当然需要，如果不离散，则树状数组下标是直接对应nums[i]的值的，由于2*nums[i],会导致大量浪费空间
        //同时此处有*2，所以需要用long来保存值
        Set<Long> set = new TreeSet<>();
        for (int x:nums){
            set.add((long)x);
            set.add((long)2*x);
        }

        HashMap<Long, Integer> values = new HashMap<>();
        int idx = 0;
        for (long x:set){
            values.put(x, idx++);
        }

        int count = 0;

        FenwickTree tree = new FenwickTree(values.size());
//        int right = values.get((long)maxNum);
//        for (int i = 0; i < nums.length; i++) {
//            int left = values.get((long)nums[i]*2);
//            right = values.size() - 1;
//            count += tree.query(right+1) - tree.query(left+1);
//            tree.update(values.get((long)nums[i])+1, 1);
//        }

        for (int i = 0; i < nums.length; i++) {
            int left = values.get((long) nums[i] * 2), right = values.size() - 1;
            count += tree.query(right + 1) - tree.query(left + 1);
            tree.update(values.get((long) nums[i]) + 1, 1);
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

        public long query(int i){
            long sum = 0;
            while(i!=0){
                sum += tree[i];
                i -= lowbit(i);
            }
            return sum;
        }

        private int lowbit(int x){
            return (x&(-x));
        }

    }




    public int solutionWithSegmentTree(int[] nums){
        Set<Long> set = new HashSet<>();
        for (int num : nums) {
            set.add((long)num);
            set.add((long)num*2);
        }

        HashMap<Long, Integer> map = new HashMap<>();
        int index = 1;
        for (Long value : set) {
            map.put(value, index++);
        }

        SegmentTree tree = new SegmentTree(map.size()+10);
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            index = map.get((long)2*nums[i]);

            //取得对应的前缀和，需要+1
            count +=  tree.getSum(index+1);

            //nums[i]值对应的树状数组下标值加1，由于树状数组表示的是前序和，所以也就是其后面一个数的所有值+1
            tree.update(map.get((long)nums[i])+1);
        }

        return count;
    }

    //二刷
    public class SegmentTree{
        int[] tree;
        int size;
        int totalCount = 0;

        public SegmentTree(int size){
            this.size = size;
            tree = new int[size];
        }

        private int lowbit(int x){
            return x&(-x);
        }

        private void update(int i){
            while(i<size){
                tree[i] += 1;
                i += lowbit(i);
            }
            totalCount += 1;
        }

        private int getSum(int i){
            int sum = 0;
            while(i>0){
                sum += tree[i];
                i -= lowbit(i);
            }
            return totalCount - sum;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
