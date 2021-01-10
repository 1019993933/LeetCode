package com.study.avltree;

import java.util.Arrays;

public class TestValTree {
    public static void main(String[] args)
    {
        AVLTree tree = new AVLTree();
        tree.initTree(Arrays.asList(54, 65, 45, 38, 32, 27, 15, 9, 74, 8, 7), true);
        tree.checkTree();
        tree.printTree();

        //, 27, 25, 15
    }
}
