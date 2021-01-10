// 在无限长的数轴（即 x 轴）上，我们根据给定的顺序放置对应的正方形方块。
//
// 第 i 个掉落的方块（positions[i] = (left, side_length)）是正方形，其中 left 表示该方块最左边的点位置(posit
// ions[i][0])，side_length 表示该方块的边长(positions[i][1])。
//
// 每个方块的底部边缘平行于数轴（即 x 轴），并且从一个比目前所有的落地方块更高的高度掉落而下。在上一个方块结束掉落，并保持静止后，才开始掉落新方块。
//
// 方块的底边具有非常大的粘性，并将保持固定在它们所接触的任何长度表面上（无论是数轴还是其他方块）。邻接掉落的边不会过早地粘合在一起，因为只有底边才具有粘性。
//
//
//
//
// 返回一个堆叠高度列表 ans 。每一个堆叠高度 ans[i] 表示在通过 positions[0], positions[1], ..., positio
// ns[i] 表示的方块掉落结束后，目前所有已经落稳的方块堆叠的最高高度。
//
//
//
//
//
// 示例 1:
//
// 输入: [[1, 2], [2, 3], [6, 1]]
// 输出: [2, 5, 5]
// 解释:
//
// 第一个方块 positions[0] = [1, 2] 掉落：
// _aa
// _aa
// -------
// 方块最大高度为 2 。
//
// 第二个方块 positions[1] = [2, 3] 掉落：
// __aaa
// __aaa
// __aaa
// _aa__
// _aa__
// --------------
// 方块最大高度为5。
// 大的方块保持在较小的方块的顶部，不论它的重心在哪里，因为方块的底部边缘有非常大的粘性。
//
// 第三个方块 positions[1] = [6, 1] 掉落：
// __aaa
// __aaa
// __aaa
// _aa
// _aa___a
// --------------
// 方块最大高度为5。
//
// 因此，我们返回结果[2, 5, 5]。
//
//
//
//
// 示例 2:
//
// 输入: [[100, 100], [200, 100]]
// 输出: [100, 100]
// 解释: 相邻的方块不会过早地卡住，只有它们的底部边缘才能粘在表面上。
//
//
//
//
// 注意:
//
//
// 1 <= positions.length <= 1000.
// 1 <= positions[i][0] <= 10^8.
// 1 <= positions[i][1] <= 10^6.
//
//
//
// Related Topics 线段树 Ordered Map
// 👍 50 👎 0

// leetcode submit region begin(Prohibit modification and deletion)

import java.util.*;

class Solution {
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

        public int getCurHeight(){
            return this.lazVal>0? this.lazVal: val;
        }

        // 定义两个辅助方法，一个是当前线段树节点是否被待计算区间包含，如果包含则可以直接返回
        public boolean containedBy(TreeNode obj) {
            return obj.contain(this);
        }

        // 包含目标单点，用于更新操作
        public boolean contain(int left) {
            return this.left <= left && this.right >= left;
        }

        // 当前区间范围为1才是真正的叶子节点
        public boolean isLeaf() {
            return this.left == this.right;
        }

        // 当前线段树节点与待计算区间是否有交集
        public boolean withNoCommon(int left, int right) {
            return (this.left > right || this.right < left);
        }

        public boolean contain(TreeNode obj) {
            return this.left <= obj.left && this.right >= obj.right;
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
        return root.getCurHeight();
    }

    private void update(TreeNode target) {
        doUpdate(root, target);
    }

    // 递归实现更新
    private void doUpdate(TreeNode curNode, TreeNode target) {
        // 只有在有交集才需要处理
        if (curNode!=null && curNode.hasCommon(target)) {
            // 如果当前节点已经是叶子节点，直接更新返回了
            if (curNode.isLeaf()) {
                updateNodeHeight(curNode, target.val);
                return;
            }
            else if (curNode.containedBy(target)){
                updateNodeHeight(curNode, target.val);
                return;
            }
            else {
                // 区间不同且有交集，则需要对递归到子节点，此时首先生成子节点并将lazVal下发
                dispatchLazval(curNode);

                doUpdate(curNode.leftNode, target);
                doUpdate(curNode.rightNode, target);

                // 更新当前节点的值，其值会反应lazVal下发后及target.val更新后对应的值
                curNode.val = Math.max(curNode.leftNode.getCurHeight(), curNode.rightNode.getCurHeight());
            }
        }
    }

    private void buildChild(TreeNode curNode) {
        // 不是叶子节点，则必然可以分解成两个子区间，计算子区间分界点
        int mid = (curNode.left + curNode.right) >> 1;

        // 由于懒标记的存在，子节点是可以为空的，即没有被初始化过
        if (curNode.leftNode == null) {
            curNode.leftNode = new TreeNode(curNode.left, mid);
        }
        if (curNode.rightNode == null) {
            curNode.rightNode = new TreeNode(mid + 1, curNode.right);
        }
    }

    private void updateNodeHeight(TreeNode curNode, int val){
        if (curNode!=null && curNode.isLeaf()){
            curNode.val = val;
            curNode.lazVal = 0;
        }
        else{
            curNode.val = 0;
            curNode.lazVal = val;
        }
    }

    private void dispatchLazval(TreeNode curNode) {
        // 不是叶子节点，则必然可以分解成两个子区间，计算子区间分界点
        buildChild(curNode);

        //如果当前存在懒标记，将标记下沉
        // 下沉到什么程度？？ 下沉到当前的查询到达叶子节点，或被目标区间完全包含
        if (curNode.lazVal > 0) {
            updateNodeHeight(curNode.leftNode, curNode.lazVal);
            updateNodeHeight(curNode.rightNode, curNode.lazVal);
            //下沉之后，清除当前节点懒标记，并设置正常高度
            curNode.val = curNode.lazVal;
            curNode.lazVal = 0;
        }
    }

    // 在当前节点区间范围查询指定区间的最大高度，只有与叶子节点比较的才是有效值
    private int query(TreeNode curNode, TreeNode target) {
        if (curNode != null && curNode.hasCommon(target)) {
            //如果当前区间包含目标区间，不用再递归子区间
            if (curNode.contain(target) && curNode.lazVal>0) {
                return curNode.lazVal;
            }
            else if (curNode.isLeaf()) {
                return curNode.val;
            } else {
                //查询子区间前，先将懒标记分发下去
                dispatchLazval(curNode);

                return Math.max(query(curNode.leftNode, target), query(curNode.rightNode, target));
            }
        }
        return 0;
    }
}
// leetcode submit region end(Prohibit modification and deletion)
