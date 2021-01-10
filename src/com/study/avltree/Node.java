package com.study.avltree;

public class Node {
    Integer value;

    Node parent;
    Node left;
    Node right;

    int height;

    public Node(Integer val) {
        this.value = val;
        this.height = 1;
    }

    public int leftHeight(){
        return  (left==null)? 0: left.height;
    }

    public int rightHeight(){
        return  (right==null)? 0: right.height;
    }

    public int bf(){
        return leftHeight()-rightHeight();
    }
}
