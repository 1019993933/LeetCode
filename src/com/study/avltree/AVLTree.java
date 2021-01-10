package com.study.avltree;

import java.util.List;

public class AVLTree {
    private Node root;
    private Integer size;

    private void addNode(Node parent, Node node, boolean heapify) {
        if (parent == null) {
            root = node;
            return;
        }

        if (node.value <= parent.value) {
            addToLeftChild(parent, node, heapify);
        } else {
            addToRightChild(parent, node, heapify);
        }
    }

    private void addToLeftChild(Node parent, Node node, boolean heapify) {
        if (parent.left == null) {
            node.parent = parent;
            parent.left = node;
            heapify(node, heapify);
        } else {
            addNode(parent.left, node, heapify);
        }
    }

    private void addToRightChild(Node parent, Node node, boolean heapify) {
        if (parent.right == null) {
            node.parent = parent;
            parent.right = node;
            heapify(node, heapify);
        } else {
            addNode(parent.right, node, heapify);
        }
    }

    public void initTree(List<Integer> data, boolean heapify) {
        for (int i = 0; i < data.size(); i++) {
            addNode(root, new Node(data.get(i)), heapify);
        }
    }

    /**
     * @return 检查树是否满足平衡树要求
     */
    public boolean checkTree() {
        boolean isValTree = checkValue(root) && checkDepth();
        System.out.println(String.format("当前是否为AVL树：%b", isValTree));
        return isValTree;
    }

    public void printTree() {
        if (root == null) {
            System.out.println("当前树为空树");
            return;
        }
        dfsPrintTree(root);
    }

    private void dfsPrintTree(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(
            String.format("%d->%s, %s", node.value, node.left == null ? "NIL" : String.valueOf(node.left.value),
                node.right == null ? "NIL" : String.valueOf(node.right.value)));
        dfsPrintTree(node.left);
        dfsPrintTree(node.right);
    }

    int maxDepth = Integer.MIN_VALUE;
    int minDepth = Integer.MAX_VALUE;

    private boolean checkDepth() {
        // 采用深度遍历来统计经过多少步骤达到NULL，求得最大值与最小值
        int curDepth = 0;
        dfs(root, curDepth);
        if (minDepth >= maxDepth) {
            return true;
        }

        return (maxDepth - minDepth <= 1);
    }

    private void dfs(Node node, int curDepth) {
        if (node == null) {
            if (curDepth < minDepth) {
                minDepth = curDepth;
            }
            if (curDepth > maxDepth) {
                maxDepth = curDepth;
            }
            return;
        }

        ++curDepth;
        dfs(node.left, curDepth);
        dfs(node.right, curDepth);
    }

    private Integer getNodeDepth(Node node) {
        if (node == root) {
            return 1;
        }

        Node tmp = node;
        int depth = 1;
        while (tmp.parent != null) {
            depth++;
            tmp = tmp.parent;
        }
        return depth;
    }

    private void addLeafNodeToList(Node node, List<Node> lst) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            lst.add(node);
        }

        addLeafNodeToList(node.left, lst);
        addLeafNodeToList(node.right, lst);
    }

    /**
     * @return 检查是否满足二叉查找树的要求
     */
    private boolean checkValue(Node node) {
        if (node == null) {
            return true;
        }
        if (node.left != null && node.value < node.left.value) {
            return false;
        }
        if (node.right != null && node.value > node.right.value) {
            return false;
        }

        return checkValue(node.left) && checkValue(node.right);
    }

//========================旋转相关
    private void heapify(Node node, boolean heapify) {
        if (!heapify){
            return;
        }

        //调整父节点的高度,只有父节点原来没有子节点的情况下，增加了子节点会导致最大高度增破坏平衡树的情况
        //否则不会增加最大高度，不需要调整
        if (node.parent==null || node.parent.height>1){
            return;
        }

        //否则递归父节点高度，再来计算平衡因子，看是否需要调整
        Node tmp = node.parent;
        while (tmp!=null){
            tmp.height++;
            tmp = tmp.parent;
        }

        //高度调整之后，再递归父节点看平衡因子是否需要调整
        tmp = node.parent;
        while (tmp!=null){
            if (tmp.bf()==2 || tmp.bf()==-2){ //破坏的位置
                //计算是属于LL,RR,LR,RL哪一种情况，只需要拿node的值分别与破坏位置，及破坏位置的子节点比较即可
                if (tmp.value>=node.value) //L
                {
                    //肯定在破坏节点的左孩子下，tmp.left不可能为空
                    if (tmp.left.value>=node.value)
                    {
                        LLRotate(tmp, tmp.left);
                        return;
                    }
                    else{
//                        LRRotate(tmp, tmp.right);
                    }
                }
                else
                {
                    if (tmp.right.value>=node.value)
                    {
//                        RLRotate(tmp, tmp.left);
                    }
                    else{
//                        RRRotate(tmp, tmp.right);
                    }

                }
            }
            tmp = tmp.parent;
        }
    }

    //单向右旋
    private void LLRotate(Node parent, Node pleft) {

        System.out.println("进入单向右旋------");
        pleft.parent = parent.parent;
        if (pleft.parent==null){
            root = pleft;
        }

        if (pleft.parent!=null){
            pleft.parent.left = pleft;
        }

        parent.parent = pleft;
        parent.left = pleft.right;

        if (pleft.right!=null) {
            pleft.right.parent = parent;
        }


        pleft.right = parent;


        //旋转完成后破坏节点对应的父节点高度减一
        Node tmp = pleft.parent;
        while (tmp!=null)
        {
            tmp.height--;
            tmp = tmp.parent;
        }

        //右子节点高度要减二
        tmp = parent;
        parent.height = Math.max(parent.rightHeight(), parent.leftHeight()) + 1;
//        downHeight(parent);
    }

    private void downHeight(Node parent) {
        if(parent!=null)
        {
            parent.height = parent.height - 2;
            downHeight(parent.left);
            downHeight(parent.right);
        }
    }
}
