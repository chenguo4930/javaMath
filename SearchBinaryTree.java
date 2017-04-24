/*
 * SearchBinaryTree    2017-04-24
 * Copyright(c) 2017 Chengguo Co.Ltd. All right reserved.
 *
 */
package com.iqtogether.qxueyou;

import java.util.NoSuchElementException;

/**
 * class description here
 *
 * @author cheng
 * @version 1.0.0
 * @since 2017-04-24
 */
public class SearchBinaryTree {

    private TreeNode root;

    //创建查找二叉树
    public TreeNode put(int data) {
        TreeNode node = null;
        TreeNode parent = null;
        if (root == null) {
            node = new TreeNode(0, data);
            root = node;
            return node;
        }
        node = root;
        while (node != null) {
            parent = node;
            if (data > node.data) {
                node = node.rightChild;
            } else if (data < node.data) {
                node = node.leftChild;
            } else {
                return node;
            }
        }
        //while完了之后就找到了该放置的位置
        node = new TreeNode(0, data);
        if (data < parent.data) {
            parent.leftChild = node;
        } else {
            parent.rightChild = node;
        }
        node.parent = parent;
        return node;
    }

    public void deleteNode(int key) {
        TreeNode node = searchNode(key);
        if (node == null) {
            throw new NoSuchElementException();
        } else {
            delete(node);
        }
    }

    /**
     * 删除某个节点
     *
     * @param node
     */
    private void delete(TreeNode node) {
        if (node == null) {
            throw new NoSuchElementException();
        } else {
            TreeNode parent = node.parent;
            if (node.leftChild == null && node.rightChild == null) {
                if (parent.leftChild == node) {
                    parent.leftChild = null;
                } else {
                    parent.rightChild = null;
                }
                return;
            }
            //被删除的节点有左无右
            if (node.leftChild != null && node.rightChild == null) {
                if (parent.leftChild == node) {
                    parent.leftChild = node.leftChild;
                } else {
                    parent.rightChild = node.leftChild;
                }
            }
            //被删除的节点有右无左
            if (node.leftChild == null && node.rightChild != null) {
                if (parent.leftChild == node) {
                    parent.leftChild = node.rightChild;
                } else {
                    parent.rightChild = node.rightChild;
                }
            }
            //左右还在都在
            //找到后继节点
            TreeNode next = getNextNode(node);
            delete(next);
            node.data = next.data;
        }
    }

    /**
     * 获取某个节点的后继节点
     *
     * @param node
     * @return
     */
    private TreeNode getNextNode(TreeNode node) {
        if (node == null) {
            return null;
        } else {
            if (node.rightChild != null) {
                //找该树里最小的元素
                return getMinTreeNode(node.rightChild);
            } else {
                TreeNode parent = node.parent;
                while (parent != null && node == parent.rightChild) {
                    node = parent;
                    parent = parent.parent;
                }
                return parent;
            }
        }
    }

    /**
     * 查找最小node
     *
     * @param node
     * @return
     */
    private TreeNode getMinTreeNode(TreeNode node) {
        if (node == null) {
            return null;
        } else {
            while (node.leftChild != null) {
                node = node.leftChild;
            }
        }
        return node;
    }

    /**
     * 查找某个节点
     *
     * @param key
     */
    private TreeNode searchNode(int key) {
        TreeNode node = root; //根节点
        if (node == null) {
            return null;
        } else {
            while (node != null && key != node.data) {
                if (key < node.data) {
                    node = node.leftChild;
                } else {
                    node = node.rightChild;
                }
            }
        }
        return node;
    }


    public void mideOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        mideOrder(node.leftChild);
        System.out.print(node.data + " ");
        mideOrder(node.rightChild);
    }

    public TreeNode getRoot() {
        return root;
    }

    class TreeNode {
        private int key;
        private TreeNode leftChild;
        private TreeNode rightChild;
        private TreeNode parent;
        private int data;

        public TreeNode(int key, int data) {
            this.key = key;
            this.data = data;
            this.rightChild = null;
            this.parent = null;
            this.leftChild = null;
        }


        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
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

        public TreeNode getParent() {
            return parent;
        }

        public void setParent(TreeNode parent) {
            this.parent = parent;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }
}