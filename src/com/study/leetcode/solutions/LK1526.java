package com.study.leetcode.solutions;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;

public class LK1526 {
    @Test
    public void test()
    {
        int[]data = new int[]{7,10,3,6,3,3,3,5,5,5,9};
        int result = minNumberOperations(data);
        ;
    }
    public int minNumberOperations(int[] target) {
        int count = 0;
        SegmentTree tree = new SegmentTree(target);
        while(tree.getCurHeight()>0){
            count += tree.update();
        }

        return count;

    }

    public class TreeNode{
        int height;
        int left;
        int right;

        public TreeNode(int left, int right){
            this.left = left;
            this.right = right;
            this.height = height;
        }
    }

    public class SegmentTree{
        TreeNode[] tree;

        public SegmentTree(int[] target){
            tree = new TreeNode[target.length*4];
            buildTree(target, 1, 0, target.length-1);
        }

        public int getCurHeight(){
            return tree[1].height;
        }

        private void buildTree(int[] target, int i, int left, int right){
            tree[i] = new TreeNode(left, right);
            if (left==right){
                tree[i].height = target[left];
            }
            else{
                int mid = (left+right)>>1;
                buildTree(target, 2*i, left, mid);
                buildTree(target, 2*i+1, mid+1, right);
                tree[i].height = Math.max(tree[2*i].height, tree[2*i+1].height);
            }
        }

        private int update(){
            int count = 0;
            Set<Integer> set = new TreeSet<>();
            doUpdate(1, set);
            int[] result = set.stream().mapToInt(Integer::intValue).toArray();
            for (int i = 0; i < result.length-1; i++) {
                if (result[i]+1!=result[i+1]){
                    ++count;
                }
            }
            ++count;
            return count;
        }

        private void doUpdate(int i, Set<Integer> set){
            if (tree[i].height<=0){
                return;
            }

            tree[i].height -= 1;
            if (tree[i].left==tree[i].right){
                set.add(tree[i].left);
            }
            else{
                doUpdate(2*i, set);
                doUpdate(2*i+1, set);
            }
        }
    }
}
