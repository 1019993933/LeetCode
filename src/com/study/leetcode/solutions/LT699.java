package com.study.leetcode.solutions;

import java.util.*;

import org.junit.jupiter.api.Test;
// 18:39 info
// 解答成功:
// 执行耗时:28 ms,击败了42.39% 的Java用户
// 内存消耗:39.2 MB,击败了79.78% 的Java用户
public class LT699 {

    @Test
    public void test() {
        int[] p1 = new int[] {1, 2};
        int[] p2 = new int[] {2, 3};
        int[] p3 = new int[] {6, 1};
        int[][] positions = new int[3][2];
        positions[0] = new int[] {1, 5};
        positions[1] = new int[] {2, 2};
        positions[2] = new int[] {7, 5};

        fallingSquares(positions);
    }

    static class TreeNode {
        int val; // 当前值

        // lazVal的作用在于实现懒标记，如果在更新区间时如果找到一个区间与目标区间完全相同，则我们只需要更新lazVal，而不需要更新该区间对应的子区间
        // 这样的作用在于如果后续的查找都只涉及到此区间非交集区间，或父区间时，我们不需要再往此区间下面遍历
        // 只有在涉及到更新或查询此区间的子区间时，我们才需要将 lazVal进行往子区间进行刷新再返回
        // 这样就可提升效率，比如在线段范围【1，100】内更新了【1，50】，后一次的查询是[51,90],那么【1，50】的值可以不用往子区间刷新，这样可以提升查询效率
        // 同时也可以节省大量空间

        // 能够使用懒标记的场景要求是我们每次对于区间内单个元素更新的值是相同的，如果不相同，则不能使用懒标记，因为后面刷新的时候无法确定每个子
        // 区间需要刷新的值
        int lazVal; // 懒加载的值

        int left;
        int right;
        TreeNode parent;
        TreeNode leftNode;
        TreeNode rightNode;

        public TreeNode(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public TreeNode(int left, int right, int val) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        // 定义两个辅助方法，一个是当前线段树节点是否被待计算区间包含，如果包含则可以直接返回
        public boolean containedBy(TreeNode obj) {
            return obj.contain(this);
        }

        // 包含目标单点，用于更新操作
        public boolean contain(TreeNode obj) {
            return this.left <= obj.left && this.right >= obj.right;
        }

        // 当前区间范围为1才是真正的叶子节点
        public boolean isLeaf() {
            return this.left == this.right;
        }

        // 当前线段树节点与待计算区间是否有交集
        public boolean withNoCommon(int left, int right) {
            return (this.left > right || this.right < left);
        }

        public boolean withNoCommon(TreeNode obj) {
            return withNoCommon(obj.left, obj.right);
        }

        // 有公共区域
        public boolean hasCommon(TreeNode obj) {
            return (this.left <= obj.left && this.right >= obj.left)
                || (obj.left <= this.left && obj.right >= this.left);
        }

        public boolean isSameRange(TreeNode obj) {
            return this.left == obj.left && this.right == obj.right;
        }

        public boolean isLeft(TreeNode obj) {
            return this.right < obj.left;
        }

        public boolean isRight(TreeNode obj) {
            return this.left > obj.right;
        }
    }

    TreeNode root;
    // 线段树对应的区间范围，这个是在一开始建树就必须确定的，即线段树结构是一个完全由初始区间确定的唯一结构，其结构是不可以动态修改的
    // 对应一个1~100， 和1~200的区间，将会生成完成不同的线段树
    // 线段树的终极意义其实就是将一个区间分解成为一根平衡二叉树，所有的叶子节点构成区间的点集，所有的非叶子节点构建区间的子集，根节点是整个区间
    // 对于该区间的任意一个目标子区间，我们都能从线段树中找到一个对应的节点序列，使得其区间和==目标区间

    // 树的规模与区间范围的长度成2^log(n)比例关系，所以对于大区间，需要进行离散化处理
    int minIndex = Integer.MIN_VALUE;
    int maxIndex = Integer.MAX_VALUE;

    // 每个新的方块A降落后会产生以下两种情况 ：
    // 1.与其他已下降的某方块B重叠
    // 2.不重叠，产生一个新的x轴上的独立元素C

    // 假设以一个连续且高度相同的区间作为一个元素进行考虑，对上面两种情况 ，情况2生成一个独立的元素，已知当前的最大高度，只需要拿新下除的元素与最大高度比较即可得到新的最大高度
    // 情况1，又分为两种情况，将该新元素其重叠的元素进行比较，
    // 1.1 如果区间相同完全重合，则不产生新的元素，只需要更新原来元素的高度，并与最大高度比较
    // 1.2 如果只是部分重叠，将原来的元素分解成为A1,A2两部分，其中A1是与B完全不重叠的部分，A2是与B完全重合的部分，则只需要更新原来的A的区间为A1,同时拿A2与B作为新的元素，计算其高度与最大高度

    // 通过以上分析，可以以二叉树的结构来进行存储计算，树节点保存区间的高度，其中定义元素必须位于叶子节点，其描述的是某个同高度的连续区间
    // 父节点包含两个前后关系的子区间（叶子节点或非叶子节点），左子节点区间位于右子节点区间左侧，其区间为包含两个子节点的最小区间，即左子节点的左值到右子节点的右值，其高度为子节点的最大高度，根节点即为当前区间的最大高度

    // 每一个新的树节点需要添加进树时，先与根节点进行区间比较，如果没有重叠，则将该树节点作为根节点的兄弟节点加入，生成一个新的根节点
    // 如果有重叠，判断其是否与左右子树重叠，一种情况是区间正好位于左右节点中间且没有重叠，则新左节点与新节点合并为兄弟节点，生成一个新的父节点代替原来的子节点插入到树中，同时判断是否需要更新最大高度

    Map<Integer, Integer> idxMap = new HashMap<>();

    public List<Integer> fallingSquares(int[][] positions) {
        if (positions == null || positions.length == 0) {
            return new ArrayList<Integer>();
        }
        List<Integer> ans = new ArrayList<>();

        // 离散化区间范围,每一个边界点都加入并排序，这样就可以用一段连续空间来表示原始空间，所有原始空间中未重叠的区间都被消除掉
        Set<Integer> set = new TreeSet<>();
        for (int[] position : positions) {
            set.add(position[0]); // 区间起点
            set.add(position[1] + position[0] - 1); // 区间终点，p[1] = end - p[0] + 1, end= p[1] + p[0] - 1;
        }

        int idx = 0; // 超始序号定义成啥并不重要
        for (Integer positon : set) {
            idxMap.put(positon, idx++);
        }

        // 初始化树
        root = new TreeNode(0, idxMap.size() - 1);

        // 遍历处理原问题
        for (int i = 0; i < positions.length; i++) {
            int leftIndex = idxMap.get(positions[i][0]);
            int rightIndex = idxMap.get(positions[i][0] + positions[i][1] - 1);
            TreeNode node = new TreeNode(leftIndex, rightIndex, positions[i][1]);

            // 先查出当前最大高度
            node.val += query(root, node);
            update(node);
            ans.add(getCurMaxHeight());
        }

        return ans;
    }

    private int getCurMaxHeight() {
        // root为空对应的是
        return root.val + root.lazVal;
    }

    private void update(TreeNode target) {
        doUpdate(root, target);
    }

    // 递归实现更新
    private void doUpdate(TreeNode curNode, TreeNode target) {
        if (curNode == null) {
            return;
        }

        // 只有在有交集才需要处理
        if (curNode.hasCommon(target)) {
            // 如果区间完全相同，则只需要更新懒标记
            if (curNode.isSameRange(target)) {
                curNode.lazVal = target.val;
            } else {
                // 如果当前节点已经是叶子节点，直接更新返回了
                if (curNode.isLeaf()) {
                    curNode.val = target.val;
                    curNode.lazVal = 0;
                    return;
                }

                // 区间不同且有交集，则需要对递归到子节点，此时首先生成子节点并将lazVal下发
                dispatchLazval(curNode);

                doUpdate(curNode.leftNode, target);
                doUpdate(curNode.rightNode, target);

                // 更新当前节点的值，其值会反应lazVal下发后及target.val更新后对应的值
                curNode.val = Math.max(curNode.leftNode.val + curNode.leftNode.lazVal,
                    curNode.rightNode.val + curNode.rightNode.lazVal);
            }
        }
    }

    private void dispatchLazval(TreeNode curNode) {
        // 不是叶子节点，则必然可以分解成两个子区间，计算子区间分界点
        int mid = (curNode.left + curNode.right) >> 1;

        // 由于懒标记的存在，子节点是可以为空的，即没有被初始化过
        if (curNode.leftNode == null) {
            curNode.leftNode = new TreeNode(curNode.left, mid);
        }
        if (curNode.rightNode == null) {
            curNode.rightNode = new TreeNode(mid + 1, curNode.right);
        }
        if (curNode.lazVal > 0) {
            if (curNode.leftNode.isLeaf()){
                curNode.leftNode.val = curNode.lazVal;
            }
            else{
                curNode.leftNode.lazVal = curNode.lazVal;
            }

            if (curNode.rightNode.isLeaf()){
                curNode.rightNode.val = curNode.lazVal;
            }
            else{
                curNode.rightNode.lazVal = curNode.lazVal;
            }

            curNode.val = curNode.lazVal;
            curNode.lazVal = 0;
        }
    }

    // 在当前节点区间范围查询指定区间的最大高度，只有与叶子节点比较的才是有效值
    private int query(TreeNode node, TreeNode newNode) {
        // 当前范围为空，或与目标范围没有交集，返回0
        if (node == null || node.withNoCommon(newNode)) {
            return 0;
        } else {
            // 这个条件，是实现懒标记作用的，即如果区间完全相同，则不需要往子节点递归了
            if (node.isSameRange(newNode)){
                return node.lazVal>0? node.lazVal:node.val;
            }
            else if (node.isLeaf()) {
                return node.lazVal>0? node.lazVal:node.val;
            } else {
                // 如果有交集且不同，则分别去左右子节点查找，分以下三种情况
                // 只与左子节点有交集，则右子节点由于第一步的条件判断会返回0
                // 只与右子节点有交集，左子节点由于第一步的条件判断返回0
                // 与左右子节点有交集，分别返回左右节点最大值比较

                // 区间不同且有交集，则需要对递归到子节点，此时首先生成子节点并将lazVal下发
                dispatchLazval(node);

                return Math.max(query(node.leftNode, newNode), query(node.rightNode, newNode));
            }
        }
    }
}
