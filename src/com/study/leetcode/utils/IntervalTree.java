package com.study.leetcode.utils;

/**
 * 线段树
 */
public class IntervalTree {
    TreeNode[] tree;

    /**
     * 初始化，建树
     * 
     * @param nums
     */
    public IntervalTree(int[] nums) {
        if (nums != null) {
            // 注意此处需要初始化的长度，对于n个元素，其生成的线段树中，必然有n个叶子节点，且分布在一棵平衡二叉树上，
            // 则最后一层的元数个数必须满足 2^(k-1)>=n，k为深度且定义根节点深度为1；则有log(2^(k-1))>=logN, k-1>=logN,k>=logN+1
            // 取k为比logN+1大的最小整数，则k=logN+2;
            // 对于深度为K的二叉数，其总的元数数量为2^k-1(k>=1),k=longN+2,这便是线段树所需要开的空间，当n=10时，k=5;所需空间为31

            int size = (int)Math.pow(2, (int)(Math.log(nums.length) / Math.log(2) + 2));
            tree = new TreeNode[size];
            buildTree(nums, 1, 0, nums.length - 1);
        }
    }

    /**
     * @param nums
     *            原始数组
     * @param i
     *            线段树序号 从1开始
     * @param left
     *            当前序号线段树节点对应的左区间
     * @param right
     *            当前序号线段树节点对应的右区间
     */
    private void buildTree(int[] nums, int i, int left, int right) {
        tree[i] = new TreeNode(left, right);

        if (left == right) {
            tree[i].sum = nums[left];
            return;
        }
        int mid = (left + right) >> 1;
        buildTree(nums, 2 * i, left, mid);
        buildTree(nums, 2 * i + 1, mid + 1, right);
        tree[i].sum = tree[i * 2].sum + tree[i * 2 + 1].sum;
    }

    /*
    线段树支持的操作
        1: 区间查找 单点查找
        2: 单点更新 区间更新
     */

    //1. 区间求和
    public long getRangeSum(int left, int right) {
        //从根节点开始遍历
        return getRangeSum(1, left, right);
    }

    //2.区间更新,将目标区间的每个元素更新为增量K。
    // 更新的目的是需要更新到叶子节点上，这样才会下次计算时能获取到正确的叶子节点值，同时对于叶子节点的父节点也必须相应更新
    public void update(int left, int right, int val){
        //计算逻辑可以从找叶子节点着手，
        // 1、对于一个满二叉树组，其叶子节点肯定是从size/2的位置开始，而对于一个平衡二叉树，应该大概在size/4的位置开始
        // 但是由于叶子节点分布在最底下二层，其区间对应关系并不具备连续性，序号小的可能在在数组的后面，所以需要每次都遍历，同时找到叶子节点
        // 后还需要往上遍历父节点增加相应的值，总体代价相对较高（遍历(O(n/4) + 父节点更新，k为高度O(k))*O(right-left)
        // 2、对每一个序号，从根节点开始二分查找，同时即更新父节点值，总体复杂度为 O(k)*O(right-left)
        // 所以其实是区间更新实际上是要转换为单点更新
        for (int i = left; i <= right; i++) {
            update(i, val);
        }

    }

    //单点更新
    public void update(int target, int val){
        doUpdate(1, target, val);

    }

    //从上往下遍历，每经过的一次节点增加val, 直到叶子节点
    private void doUpdate(int i, int target, int val){
        //只有当前线段包含目标序号，才需要更新
        if (tree[i].contain(target)){
            tree[i].sum += val;
            //已经到叶子节点了，返回
            if (tree[i].isLeaf()){
                return;
            }
            else{
                //否则更新子节点,由于当前不是叶子节点，则必然会有子节点且被子节点包含
                //且必须只有一侧会包含单点，所以提前判断吧，少一次递归深度
                if (tree[2*i].contain(target)){
                    doUpdate(2*i, target, val);
                }
                else{
                    doUpdate(2*i+1, target, val);
                }
            }
        }
    }

    //3.单点更新与单点求和其实是对应区间更新与区间求和的简化版本，设left==right即可
    public long getSum(int i)
    {
        return getRangeSum(i, i);
    }



    //这个递归方法会把待计算的区间分解成一个个完全被待计算区间包含子区间，且所有子区间相加正好等于待计算区间与根节点的交集（即当前线段树最大区间与计算区间的公共部分）
    private long getRangeSum(int i, int left, int right){
        //如果当前待计算的线段树节点已经超出定义的范围，直接返回0, 由于后面的包含与交集条件约束，此处的i必然不会超界
        if (i<=0 || i>=tree.length){
            return 0;
        }

        long sum = 0;
        //当前节点被待计算区间包含，则直接返回
        TreeNode curTreeNode = tree[i];
        if (curTreeNode.containedBy(left, right)){
            return curTreeNode.sum;
        }

        //如果没有交集，则直接返回0
        if (curTreeNode.withNoCommon(left, right)){
            return 0;
        }

        //有交集，但是又不完全被待计算的区间包含，则包含计算两个子节点的值相加
        return getRangeSum(2*i, left, right) + getRangeSum(2*i+1, left, right);

    }

    class TreeNode {
        int sum;
        int left;
        int right;

        public TreeNode(int left, int right) {
            this.left = left;
            this.right = right;
        }

        //定义两个辅助方法，一个是当前线段树节点是否被待计算区间包含，如果包含则可以直接返回
        public boolean containedBy(int left, int right) {
            return (this.left>=left && this.right<=right);
        }

        //包含目标单点，用于更新操作
        public boolean contain(int left){
            return this.left<=left && this.right>=left;
        }

        //当前是叶子节点
        public boolean isLeaf(){
            return this.left==this.right;
        }

        //当前线段树节点与待计算区间是否有交集
        public boolean withNoCommon(int left, int right) {
            return (this.left>right || this.right<left);
        }
    }
}
