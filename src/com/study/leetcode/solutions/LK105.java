package com.study.leetcode.solutions;

import com.study.leetcode.utils.TreeNode;
import org.junit.jupiter.api.Test;

public class LK105 {

    @Test
    public void test()
    {
        int[] preOrder = new int[]{3,9,20,15,7};
        int[] inOrder = new int[]{9,3,15,20,7};

        buildTree(preOrder, inOrder);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length <= 0) {
            return null;
        }

        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
        TreeNode root = new TreeNode(preorder[pStart]);
        if (pStart == pEnd) {
            return root;
        }

        int inOrderInde = findIndex(inorder, preorder[pStart]);
        int leftLen = inOrderInde - iStart;
        int rightLen = iEnd - inOrderInde;

        if (leftLen > 0) {
            TreeNode leftNode =
                    buildTree(preorder, pStart + 1, pStart + leftLen , inorder, iStart, inOrderInde - 1);
            root.left = leftNode;
        }
        if (rightLen > 0) {
            TreeNode rightNode = buildTree(preorder, pStart + leftLen + 1, pEnd, inorder, inOrderInde + 1, iEnd);
            root.right = rightNode;
        }

        return root;
    }

    private int findIndex(int[] data, int val) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == val) {
                return i;
            }
        }
        return -1;
    }
}
