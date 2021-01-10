// 城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。给你所有建筑物的位置和高度，请返回由这些建筑物形成的 天际线 。
//
// 每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示：
//
//
//
// lefti 是第 i 座建筑物左边缘的 x 坐标。
// righti 是第 i 座建筑物右边缘的 x 坐标。
// heighti 是第 i 座建筑物的高度。
//
//
// 天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。关键点是水平线段的左端点。
// 列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
//
// 注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答
// 案；三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]
//
//
//
// 示例 1：
//
//
// 输入：buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
// 输出：[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
// 解释：
// 图 A 显示输入的所有建筑物的位置和高度，
// 图 B 显示由这些建筑物形成的天际线。图 B 中的红点表示输出列表中的关键点。
//
// 示例 2：
//
//
// 输入：buildings = [[0,2,3],[2,5,3]]
// 输出：[[0,3],[5,0]]
//
//
//
//
// 提示：
//
//
// 1 <= buildings.length <= 104
// 0 <= lefti < righti <= 231 - 1
// 1 <= heighti <= 231 - 1
// buildings 按 lefti 非递减排序
//
// Related Topics 堆 树状数组 线段树 分治算法 Line Sweep
// 👍 318 👎 0

import java.util.*;

// leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 坐标与高度对应的MAP
    HashMap<Integer, Integer> map = new HashMap<>();
    HashMap<Integer, Integer> idxToPos = new HashMap<>();

    // 坐标点集合，按从小到大
    Set<Integer> set = new TreeSet<>();

    //保存结果子区间的数组
    List<TreeNode> resultList = new ArrayList<>();

    public List<List<Integer>> getSkyline(int[][] buildings) {
        if (buildings == null || buildings.length == 0) {
            return null;
        }

        //建树
        buildTree(buildings);
        //更新
        for (int[] building : buildings) {
            update(1, building[0], building[1]-1, building[2]);
        }
        //查询，查询到所有子区间对应的值，再对子区间进行去重处理
        query(1, min, max);
        Collections.sort(resultList, (o1, o2)->{return o1.left-o2.left;});

        List<List<Integer>> lst = new ArrayList<List<Integer>>();
        addTreeNode(lst, resultList.get(0));
        TreeNode preNode = null;
        for (TreeNode treeNode : resultList) {
            if (preNode==null){
                addTreeNode(lst, treeNode);
            }
            else{
                if (!compare(preNode, treeNode)){
                    addTreeNode(lst, treeNode);
                }
            }
            preNode = treeNode;
        }

        return lst;
    }

    //当前两个区间是否是连续且高度相同，如果是则不需要加到结果中
    private boolean compare(TreeNode preNode, TreeNode curNode){
        if (preNode.right==curNode.left){
            int preHeight = preNode.lazVal>0? preNode.lazVal: preNode.val;
            int curHeight = curNode.lazVal>0? curNode.lazVal: curNode.val;

            return preHeight==curHeight;
        }
        return false;
    }

    private void addTreeNode(List<List<Integer>> lst, TreeNode node){
        lst.add(getKeyPoint(node.left, node.lazVal>0? node.lazVal: node.val));
    }

    private List<Integer> getKeyPoint(int x, int h) {
        List<Integer> keyPoint = new ArrayList<>();
        keyPoint.add(x);
        keyPoint.add(h);
        return keyPoint;
    }

    private void createHeightMap(int pos, int height) {
        if (map.containsKey(pos)) {
            map.put(pos, Math.max(map.get(pos), height));
        } else {
            map.put(pos, height);
        }
    }

    TreeNode[] tree;
    int size;
    int max;
    int min;

    private void buildTree(int[][] buildings) {
        for (int[] building : buildings) {
            set.add(building[0]);
            set.add(building[1]);
        }
        max = set.stream().max(Integer::compareTo).get();
        min = set.stream().min(Integer::compareTo).get();

        size = max - min + 1;
        tree = new TreeNode[size * 4];
        buildTree(1, min, max);
    }

    private void query(int i, int left, int right){
        if (i>=size*4){
            return;
        }

        TreeNode curNode = tree[i];
        if (curNode==null) return;

        //没有交集
        if (curNode.left>right || curNode.right<left){
            return;
        }

        //叶子节点，直接更新
        if (curNode.left==curNode.right){
            resultList.add(curNode);
            return;
        }

        //区间相同，且有lazVal直接返回，不需要看子区间
        if (left==curNode.left && right==curNode.right && curNode.lazVal>0){
            resultList.add(curNode);
            return;
        }

        query(2*i, left, right);
        query(2*i+1, left, right);
    }

    private void update(int i, int left, int right, int val) {
        if (i >= size * 4) {
            return;
        }
        TreeNode curNode = tree[i];
        if (curNode == null) {
            return;
        }

        //没有交集，由于是左闭右开，所以可以用左值与右值相等也并不算是有交集
        if (curNode.left>right || curNode.right<left){
            return;
        }

        //叶子节点，直接更新
        if (curNode.left==curNode.right){
            curNode.val = Math.max(curNode.val, val);
            return;
        }

        //更新的时候只要更新区间比线段树区间大，就可以更新lazVal
        if (left<=curNode.left && right>=curNode.right){
            curNode.lazVal = Math.max(curNode.lazVal, val);
            return;
        }

        //如果不相等，那就往下更新吧,当前有lazVal标记那就清掉吧，意味着后面如果要查找，同样需要往子节点去查找获取值
        curNode.lazVal = 0;
        update(2*i, left, right, val);
        update(2*i+1, left, right, val);
    }

    private void buildTree(int i, int left, int right) {
        if (left <= right) {
            TreeNode node = new TreeNode(left, right);
            tree[i] = node;
            if (left == right) {
                return;
            } else {
                int mid = (left + right) >> 1;
                buildTree(2 * i, left, mid);
                buildTree(2 * i + 1, mid + 1, right);
            }
        }
    }

    public class TreeNode{
        int left;
        int right;
        int val;
        int lazVal;

        public TreeNode(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}
// leetcode submit region end(Prohibit modification and deletion)
