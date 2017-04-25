/*
 * AVLTree    2017-04-25
 * Copyright(c) 2017 Chengguo Co.Ltd. All right reserved.
 *
 */
package com.iqtogether.qxueyou;

import org.w3c.dom.Node;

/**
 * class description here
 *
 * @author cheng
 * @version 1.0.0
 * @since 2017-04-25
 */
public class AVLTree<E> {

    private Node<E> root = null;
    private int size = 0;
    private static final int LEFT_HIGH = 1;
    private static final int RIGHT_HIGH = -1;
    private static final int EQUAL_HIGH = 0;

    /**
     * @param element
     * @return
     */
    public boolean insertElement(E element) {
        Node<E> t = root;
        if (t == null) {
            root = new Node(element, null);
            size = 1;
            return true;
        }
        int cmp = 0;
        Node<E> parent;//用来保存父节点
        Comparable<? super E> e = (Comparable<? super E>) element;
        do {
            parent = t;
            cmp = e.compareTo(t.element);
            if (cmp < 0) {
                t = t.left;
            } else if (cmp > 0) {
                t = t.right;
            } else {
                return false;
            }
        } while (t != null);
        Node<E> child = new Node<>(element, parent);//要插入的子节点
        if (cmp < 0) {
            parent.left = child;
        } else if (cmp > 0) {
            parent.right = child;
        }
        //节点的位置已经找到，并且成功插入
        //开始检查平衡树的平衡性，回溯查找
        while (parent != null) {
            cmp = e.compareTo(parent.element);
            if (cmp < 0) {
                parent.balance++;
            } else {
                parent.balance--;
            }
            if (parent.balance == 0) {
                break;
            }
            if (Math.abs(parent.balance) == 2) {
                //才出现
                fixAfterInsert();
            }
            parent = parent.parent;
        }


    }

    /**
     * 根据某个不平衡节点，旋转该树，达到平衡
     */
    private void fixAfterInsert(AVLTree<E>.Node<E> parent) {
        if (parent.balance == 2) {
            leftBalance(parent);
        }
        if (parent.balance == -2) {
            rightBalance(parent);
        }
    }

    private void rightBalance(Node<E> t) {
        Node<E> rc = t.right;
        switch (rc.balance) {
            case RIGHT_HIGH:
                leftBalance(t);
                t.balance = EQUAL_HIGH;
                rc.balance = EQUAL_HIGH;
                break;
            case LEFT_HIGH:
                Node<E> ld = rc.left;//最后平衡因子
                switch (ld.balance) {
                    case LEFT_HIGH:
                        t.balance = EQUAL_HIGH;
                        rc.balance = RIGHT_HIGH;
                        break;
                    case RIGHT_HIGH:
                        t.balance = LEFT_HIGH;
                        rc.balance = EQUAL_HIGH;
                        break;
                    case EQUAL_HIGH:
                        t.balance = EQUAL_HIGH;
                        rc.balance = EQUAL_HIGH;
                        break;
                }
                ld.balance = EQUAL_HIGH;
                rightRotate(t.right);
                leftRotate(t);
                break;
        }
    }

    private void leftBalance(Node<E> t) {
        Node<E> lc = t.left;
        switch (lc.balance) {
            case LEFT_HIGH: //t的左子树的左边出现平衡的问题，直接进行右旋
                rightRotate(t);
                lc.balance = EQUAL_HIGH;
                t.balance = EQUAL_HIGH;
                break;
            case RIGHT_HIGH:
                Node<E> rd = lc.right;
                switch (rd.balance) {
                    case LEFT_HIGH:
                        lc.balance = EQUAL_HIGH;
                        t.balance = RIGHT_HIGH;
                        rd.balance = EQUAL_HIGH;
                        break;
                    case RIGHT_HIGH:
                        t.balance = EQUAL_HIGH;
                        rd.balance = EQUAL_HIGH;
                        lc.balance = LEFT_HIGH;
                        break;
                    case EQUAL_HIGH:
                        t.balance = EQUAL_HIGH;
                        lc.balance = EQUAL_HIGH;
                        rd.balance = EQUAL_HIGH;
                        break;
                }

                //先左旋再右旋
                leftRotate();
                break;
        }
    }

    private void leftRotate(Node<E> p) {
        if (p != null) {
            Node<E> rc = p.right;
            p.right = rc.left; //把中间夹的多余的元素连接到p右下角
            if (rc.left != null) {
                rc.left.parent = p;
            }
        }
    }

    private void rightRotate(Node<E> t) {

    }

    class Node<E> {
        E element;
        int balance = 0;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }


        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }
    }
}