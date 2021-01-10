package com.study.leetcode.solutions;
import com.study.leetcode.utils.Utils;
import org.junit.jupiter.api.Test;

import java.util.*;


public class LK218 {
    @Test
    public void test()
    {
        int[][] data = Utils.toTwoDimArray("[[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]");
        getSkyline(data);

        System.out.println();
    }


    HashMap<Integer, Integer> map = new HashMap<>();
    HashMap<Integer, Integer> idxToPos = new HashMap<>();

    private List<Integer> getKeyPoint(int x, int h) {
        List<Integer> keyPoint = new ArrayList<>();
        keyPoint.add(x);
        keyPoint.add(h);
        return keyPoint;
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        if (buildings == null || buildings.length == 0) {
            return null;
        }
        List<List<Integer>> lst = new ArrayList<List<Integer>>();


        Set<Integer> set = new TreeSet<>();
        for (int[] build: buildings){
            set.add(build[0]);
            set.add(build[1]);
        }

        int idx = 0;
        for (Integer value: set){
            map.put(value, idx);
            idxToPos.put(idx, value);
            ++idx;
        }

        SegmentTree tree = new SegmentTree(map.size());
        for (int i = 0; i <= buildings.length - 1; i++) {
            int left = map.get(buildings[i][0]);
            //-1的意义在于原区间是一个左闭右开区间，所以最右边的值是不需要更新到线段树的
            //如原空间 2~3，在线段树中实际只需要更新2
            int right = map.get(buildings[i][1])-1;
            TreeNode node = new TreeNode(left, right, buildings[i][2]);
            tree.update(node);
        }

        Map<Integer, Integer> and =  tree.getSkyline();
        int[][] result = new int[and.size()][2];
        set.clear();
        set.addAll(and.keySet());
        int i = 0;
        for (Integer integer : set) {
            result[i][0] = integer;
            result[i][1] = and.get(integer);
            ++i;
        }

        lst.add(getKeyPoint(idxToPos.get(result[0][0]), result[0][1]));
        for (int j = 0; j < result.length-1; j++) {
            if (result[j+1][1]!=result[j][1]){
                lst.add(getKeyPoint(idxToPos.get(result[j+1][0]), result[j+1][1]));
            }
        }


        return lst;
    }

    //此处的区间是一个左闭右开的区间
    public class TreeNode {
        public int l;
        public int r;
        public int val;
        public int lazVal;

        public TreeNode(int l, int r) {
            this.l = l;
            this.r = r;
        }

        public TreeNode(int l, int r, int val) {
            this.l = l;
            this.r = r;
            this.val = val;
        }

        public boolean isLeas() {
            return (this.l== this.r);
        }

        public boolean isSameRange(TreeNode obj) {
            return (this.l == obj.l && this.r == obj.r);
        }

        public boolean hasCommon(TreeNode obj) {
            return (this.withoutCommon(obj) == false);
        }

        public boolean withoutCommon(TreeNode obj) {
            return (this.l > obj.r || this.r < obj.l);
        }
    }

    public class SegmentTree {
        TreeNode[] tree;
        Map<Integer, Integer> ans = new HashMap<>();

        int size;

        public SegmentTree(int size) {
            this.tree = new TreeNode[size * 4];
            buildTree(1, 0, size - 1);
            this.size=size;
        }

        public void buildTree(int i, int left, int right) {
            if (left <= right) {
                tree[i] = new TreeNode(left, right);
                if (left== right) {
                    return;
                } else {
                    int mid = ((left + right) >> 1);
                    buildTree(2 * i, left, mid);
                    buildTree(2 * i + 1, mid + 1, right);
                }
            }
        }

        public void update(TreeNode node) {
            pushdown(1, node);
        }

        public void pushdown(int i, TreeNode target) {
            if (tree[i] != null && tree[i].hasCommon(target)) {
                if (tree[i].isLeas()) {
                    tree[i].val = Math.max(tree[i].val, target.val);
                } else {
                    pushdown(2 * i, target);
                    pushdown(2 * i + 1, target);
                }
            }
        }


        public Map<Integer, Integer> getSkyline() {
            query(1);
            return ans;
        }

        public void query(int i) {
            if (i<=size*4){
            TreeNode node = tree[i];
            if (node != null) {
                if (node.isLeas()) {
                    ans.put(node.l, node.val); //区间与高度的映射
                    return;
                } else {
                    query(2 * i);
                    query(2 * i + 1);
                }
            }}
        }
    }
}