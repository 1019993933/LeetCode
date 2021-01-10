//给定一个整数数组 nums，求出数组从索引 i 到 j (i ≤ j) 范围内元素的总和，包含 i, j 两点。 
//
// update(i, val) 函数可以通过将下标为 i 的数值更新为 val，从而对数列进行修改。 
//
// 示例: 
//
// Given nums = [1, 3, 5]
//
//sumRange(0, 2) -> 9
//update(1, 2)
//sumRange(0, 2) -> 8
// 
//
// 说明: 
//
// 
// 数组仅可以在 update 函数下进行修改。 
// 你可以假设 update 函数与 sumRange 函数的调用次数是均匀分布的。 
// 
// Related Topics 树状数组 线段树 
// 👍 210 👎 0


import sun.reflect.generics.tree.Tree;

//leetcode submit region begin(Prohibit modification and deletion)
class NumArray {
    private int[] originData;

    //树状数组实现
    private FenwickTree fenwickTree;

    public NumArray(int[] nums) {
        originData = new int[nums.length];
        System.arraycopy(nums, 0, originData, 0, nums.length);
        fenwickTree = new FenwickTree(nums.length);
        for (int i = 0; i < nums.length; i++) {
            fenwickTree.update(i+1, nums[i]);
        }



    }

    //更新i位置的值为val,考虑到后面的维护，其实是要调用更新C[i]位置的差值
    public void update(int i, int val) {
        int delta = val - originData[i];
        originData[i] = val;
        fenwickTree.update(i+1, delta);
    }

    public int sumRange(int i, int j) {
        return (int)(fenwickTree.sum(j+1) - fenwickTree.sum(i));
    }


    //树状数组
    class FenwickTree {
        private int n;
        private int[] tree;

        public FenwickTree(int size){
            this.n = size + 2;
            tree = new int[this.n];
        }

        public void update(int i, int val){
            while(i<n){
                tree[i] += val;
                i += lowbit(i);
            }
        }

        public long sum(int i){
            long sum = 0;
            while(i>0){
                sum += (long)tree[i];
                i -= lowbit(i);
            }
            return sum;
        }


        private int lowbit(int x){
            return x & (-x);
        }
    }

    //线段树
    class IntervalTree{
        class TreeNode{
            public int val;
            int left;
            int right;

            public TreeNode(int left, int right){
                this.left = left;
                this.right = right;
            }

            public TreeNode(int val, int left, int right){
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }

        TreeNode root;

        public IntervalTree(int[] nums){
            if (nums!=null) {
                root = buildTree(nums, 0, nums.length-1);
            }
        }

        private TreeNode buildTree(int[] nums, int left, int right){
            if (left>right){
                return null;
            }
            else if (left=right){
                TreeNode node = new TreeNode(nums[left], left, right);
                return node;
            }
            else if (left<right){
                TreeNode node = new TreeNode(left, right);
                int mid = (left + right)>>1;
                node.left = buildTree(nums, left, mid);
                node.right = buildTree(nums, mid+1, right);

                node.val = node.left.val + node.right.val;
            }
        }
    }
}




/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
//leetcode submit region end(Prohibit modification and deletion)
