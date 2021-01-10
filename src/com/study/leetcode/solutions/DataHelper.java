package com.study.leetcode.solutions;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class DataHelper {
    public final int a = 10;

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * @param data
     *            字符串数组表示的完全二叉树，null表示无对应子节点
     * @return
     */
    public static TreeNode genTreeFromArray(Integer[] data) {
        HashMap<Integer, TreeNode> map = new HashMap<>();
        TreeNode root = null;

        for (int i = 0; i < data.length; i++) {
            TreeNode treeNode = null;
            if (data[i] != null) {
                treeNode = new TreeNode((int)data[i]);
                TreeNode parent = map.get((i + 1) / 2);
                if (parent == null) {
                    root = treeNode;
                } else {
                    if (i % 2 != 0) {
                        parent.left = treeNode;
                    } else {
                        parent.right = treeNode;
                    }
                }

                map.put(i+1, treeNode);
            }
        }

        return root;
    }

    @Test
    public void test() {
        Integer[] data = new Integer[] {1, 2, 3, 4, 5, 6, null, 8};
        TreeNode treeNode = genTreeFromArray(data);
        System.out.println();
    }
}
