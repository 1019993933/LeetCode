package com.study.leetcode.solutions;

import java.util.*;

import org.junit.jupiter.api.Test;

import com.study.leetcode.utils.Utils;


public class LK218_1 {
    @Test
    public void test()
    {
        int[][] data = Utils.toTwoDimArray("[[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]");
        getSkyline(data);

        System.out.println();
    }


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
        if (left>right){
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

        //更新的时候只要更新区间比线段树区间大，且更新的值比当前的最大值在，就可以直接更新lazVal
        if (left<=curNode.left && right>=curNode.right && val>=getMaxValue(i)){
            curNode.lazVal = Math.max(curNode.lazVal, val);
            curNode.val = 0;
            return;
        }

        //如果不相等，那就往下更新吧,当前有lazVal标记那就清掉吧，意味着后面如果要查找，同样需要往子节点去查找获取值
        if (curNode.lazVal>0) {
            dispatchChildValue(2*i, curNode.lazVal);
            dispatchChildValue(2*i+1, curNode.lazVal);
            curNode.lazVal = 0;
        }

        update(2*i, left, right, val);
        update(2*i+1, left, right, val);

        curNode.val = getMaxValue(2*i, 2*i+1);
    }

    private int getMaxValue(int i, int j){
        return Math.max(getMaxValue(i), getMaxValue(j));
    }

    private int getMaxValue(int i){
        if (i>=size*4 || tree[i]==null){
            return 0;
        }
        else {
            //当前场景lazVal为覆盖型，则val和lazVal两者只会有一个有值，取大值没毛病
            return Math.max(tree[i].val, tree[i].lazVal);
        }
    }

    private void dispatchChildValue(int i, int lazVal) {
        if (i>=size*4 || tree[i]==null) {
            return;
        }

        if (tree[i].left==tree[i].right){
            tree[i].val = Math.max(tree[i].val, lazVal);
        }else{
            if (lazVal>=getMaxValue(i)){
                tree[i].lazVal = lazVal;
                tree[i].val = 0;
            }
        }
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