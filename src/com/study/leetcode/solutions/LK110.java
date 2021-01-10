package com.study.leetcode.solutions;

import com.study.leetcode.solutions.DataHelper.TreeNode;
import org.junit.jupiter.api.Test;

public class LK110 {
    public boolean isBalanced(TreeNode root) {
        return (Math.abs(depthTree(root.left)-depthTree(root.right))<=1);
    }

    private int depthTree(TreeNode node){
        if (node==null){
            return 0;
        }
        if (node.left==node.right){
            return 1;
        }

        int depth =  1 + Math.max(depthTree(node.left), depthTree(node.right));
        System.out.println(String.format("curNode: %3d, depth: %3d", node.val, depth));
        return depth;
    }

    @Test
    public void doTestLK110(){
        TreeNode node = DataHelper.genTreeFromArray(new Integer[]{1, 2, 3, 4, 5, 6, null, 8});
        isBalanced(node);
    }
}
