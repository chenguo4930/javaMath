/*
 * Test    2017-04-24
 * Copyright(c) 2017 Chengguo Co.Ltd. All right reserved.
 *
 */
package com.iqtogether.qxueyou;

/**
 * class description here
 *
 * @author cheng
 * @version 1.0.0
 * @since 2017-04-24
 */
public class Test {

    public static void main(String[] args) {
//        BinaryTree binaryTree = new BinaryTree();
//        binaryTree.createBinaryTree();
//        binaryTree.preOrder(binaryTree.getRoot());


//        ArrayList<String> data = new ArrayList<>();
//        String[] dataArray = new String[]{"A","B","D","#","#","E","#","#","C","#","F","#","#"};
//        for (String s : dataArray) {
//            data.add(s);
//        }
//        binaryTree.createBinaryTreePre(data); //创建了二叉树
//        binaryTree.preOrder(binaryTree.getRoot());

        SearchBinaryTree binaryTree1 = new SearchBinaryTree();
        int[] array = new int[]{12, 4, 56, 2, 32, 54, 23, 34, 1, 64, 34, 56, 86, 57, 23};
        for (int i : array) {
            binaryTree1.put(i);
        }
        binaryTree1.mideOrder(binaryTree1.getRoot());
    }
}