/*
 * BinaryTree    2017-04-24
 * Copyright(c) 2017 Chengguo Co.Ltd. All right reserved.
 *
 */
package com.iqtogether.qxueyou;

import java.util.ArrayList;

/**
 * 二叉树
 *
 * @author cheng
 * @version 1.0.0
 * @since 2017-04-24
 */
public class BinaryTree {

    private TreeNode root = null;

    public BinaryTree() {
        root = new TreeNode(1, "A");
    }

    public void createBinaryTree() {
        TreeNode nodeB = new TreeNode(2, "B");
        TreeNode nodeC = new TreeNode(3, "C");
        TreeNode nodeD = new TreeNode(4, "D");
        TreeNode nodeE = new TreeNode(5, "E");
        TreeNode nodeF = new TreeNode(6, "F");

        root.leftChild = nodeB;
        root.rightChild = nodeC;
        nodeB.leftChild = nodeD;
        nodeB.rightChild = nodeE;
        nodeC.rightChild = nodeF;
    }

    public int size() {
        return getSize(root);
    }

    /**
     * 通过前序遍历的节点序列反向创建二叉树
     */
    public void createBinaryTreePre(ArrayList<String> data) {
        createBinaryTree(data.size(), data);
    }

    private TreeNode createBinaryTree(int size, ArrayList<String> data) {
        if (data.size() == 0) {
            return null;
        }
        String d = data.get(0);
        TreeNode node;
        int index = size - data.size(); //下标
        if (d.equals('#')) {
            node = null;
            data.remove(0);//删除该元素
            return node;
        }
        node = new TreeNode(index, d);//创建该节点
        if (index == 0) {
            //创建根节点
            root = node;
        }
        data.remove(0);
        node.leftChild = createBinaryTree(size, data);
        node.rightChild = createBinaryTree(size, data);
        return node;
    }

    /**
     * 获取某个节点的二叉树的节点数量
     *
     * @param node
     * @return
     */
    private int getSize(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + getSize(node.getLeftChild()) + getSize(node.getRightChild());
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder(TreeNode node) {
        if (node == null) {
            return;
        } else {
            System.out.println(node.data); //遍历该节点
            preOrder(node.getLeftChild());
            preOrder(node.getRightChild());
        }
    }

    /**
     * 中序遍历
     */
    public void midOrder(TreeNode node) {
        if (node == null) {
            return;
        } else {
            midOrder(node.getLeftChild());
            System.out.println(node.data);
            midOrder(node.getRightChild());
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder(TreeNode node) {
        if (node == null) {
            return;
        } else {
            postOrder(node.leftChild);
            postOrder(node.leftChild);
            System.out.println(node.data);
        }
    }

    public TreeNode getRoot() {
        return root;
    }

    class TreeNode {
        private int index; //根节点为1
        private String data;
        private TreeNode leftChild;
        private TreeNode rightChild;
        private TreeNode parrent; //双亲节点

        public TreeNode(int index, String data) {
            this.index = index;
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
            this.parrent = null;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public TreeNode getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(TreeNode leftChild) {
            this.leftChild = leftChild;
        }

        public TreeNode getRightChild() {
            return rightChild;
        }

        public void setRightChild(TreeNode rightChild) {
            this.rightChild = rightChild;
        }

        public TreeNode getParrent() {
            return parrent;
        }

        public void setParrent(TreeNode parrent) {
            this.parrent = parrent;
        }
    }
}