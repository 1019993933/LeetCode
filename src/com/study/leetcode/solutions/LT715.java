package com.study.leetcode.solutions;//Range 模块是跟踪数字范围的模块。你的任务是以一种有效的方式设计和实现以下接口。

import org.junit.jupiter.api.Test;

//
// 
// addRange(int left, int right) 添加半开区间 [left, right)，跟踪该区间中的每个实数。添加与当前跟踪的数字部分重叠
//的区间时，应当添加在区间 [left, right) 中尚未跟踪的任何数字到该区间中。 
// queryRange(int left, int right) 只有在当前正在跟踪区间 [left, right) 中的每一个实数时，才返回 true。 
//
// removeRange(int left, int right) 停止跟踪区间 [left, right) 中当前正在跟踪的每个实数。 
// 
//
// 
//
// 示例： 
//
// addRange(10, 20): null
//removeRange(14, 16): null
//queryRange(10, 14): true （区间 [10, 14) 中的每个数都正在被跟踪）
//queryRange(13, 15): false （未跟踪区间 [13, 15) 中像 14, 14.03, 14.17 这样的数字）
//queryRange(16, 17): true （尽管执行了删除操作，区间 [16, 17) 中的数字 16 仍然会被跟踪）
// 
//
// 
//
// 提示： 
//
// 
// 半开区间 [left, right) 表示所有满足 left <= x < right 的实数。 
// 对 addRange, queryRange, removeRange 的所有调用中 0 < left < right < 10^9。 
// 在单个测试用例中，对 addRange 的调用总数不超过 1000 次。 
// 在单个测试用例中，对 queryRange 的调用总数不超过 5000 次。 
// 在单个测试用例中，对 removeRange 的调用总数不超过 1000 次。 
// 
//
// 
// Related Topics 线段树 Ordered Map 
// 👍 69 👎 0
//leetcode submit region begin(Prohibit modification and deletion)
class LT715 {
    private static final int RIGHT= 20;

    private static final int LEFT = 10;

    //线段树容量，按原始数据的4倍来开辟
    private static final int SIZE = 4*RIGHT+1;

    //线段树数组，
    private TreeNode[] tree = new TreeNode[SIZE];

    @Test
    public void test(){
        LT715 obj = new LT715();
        obj.addRange(10, 20);
        obj.removeRange(14, 16);
        boolean b = obj.queryRange(19, 20);
        boolean b1 = obj.queryRange(13, 15);
        boolean b2 = obj.queryRange(16, 17);
        System.out.println("fdas");
    }

    public LT715() {
        buildTree(1, LEFT, RIGHT);
    }

    public void addRange(int left, int right) {
        left = left<LEFT? LEFT: left;
        right = right>RIGHT? RIGHT: right;
        update(1, left, right, true);
    }
    
    public boolean queryRange(int left, int right) {
        return queryRange(1, left, right);
    }
    
    public void removeRange(int left, int right) {
        left = left<LEFT? LEFT: left;
        right = right>RIGHT? RIGHT: right;
        update(1, left, right, false);
    }

    private boolean queryRange(int i, int left, int right){
        if (i>=SIZE || tree[i]==null){
            return true;
        }

        TreeNode curNode = tree[i];
        if (curNode.isLeaf() && curNode.left>=left && curNode.right<=right){
            return curNode.val;
        }

        int mid= (left+right)>>1;
        if (!queryRange(leftIndex(i), left, right)){
            return false;
        }

        if (!queryRange(rightIndex(i), left, right)){
            return false;
        }

        return true;
    }

    private void update(int i, int left, int right, boolean val){
        if (i>=SIZE || tree[i]==null){
            return;
        }

        TreeNode curNode = tree[i];
        if (curNode.left>=right || curNode.right<=left){
            return;
        }

        if (curNode.isLeaf()){
            curNode.val = val;
            return;
        }

        int mid = (left+right)>>1;
        update(leftIndex(i), left, right, val);
        update(rightIndex(i), left, right, val);
    }

    private void buildTree(int i, int left, int right){
        if (left>right-1){
            return;
        }

        tree[i] = new TreeNode(left, right);
        if (tree[i].isLeaf()){
            return;
        }

        int mid = (left+right)>>1;
        buildTree(leftIndex(i), left, mid);
        buildTree(rightIndex(i), mid, right);
    }

    private int leftIndex(int i){
        return 2*i;
    }
    private int rightIndex(int i){
        return 2*i+1;
    }

    private class TreeNode{
        int left;
        int right;
        boolean val;

        public TreeNode(int left, int right){
            this.left=left;
            this.right = right;
        }

        public boolean isLeaf(){
            return this.left + 1 == this.right;
        }
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */
//leetcode submit region end(Prohibit modification and deletion)
