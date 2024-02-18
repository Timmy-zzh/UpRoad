package com.timmy.dataalg.leetcode

import com.timmy.dataalg.bean.TreeNode
import com.timmy.dataalg.preTraversal

/**
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历，
 * inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。

示例 1:
输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
输出: [3,9,20,null,null,15,7]

示例 2:
输入: preorder = [-1], inorder = [-1]
输出: [-1]
 */
fun main() {

    //    val res = buildTree(intArrayOf(3, 9, 20, 15, 7), intArrayOf(9, 3, 15, 20, 7))
    //    val res = buildTree(intArrayOf(-1), intArrayOf(-1))
    //    val res = buildTree(intArrayOf(1, 2), intArrayOf(2, 1))
    val res = buildTree(intArrayOf(1, 2), intArrayOf(1, 2))
    res?.preTraversal()
}

/**
 * 1、审题：
 * - 输入两个数组，preorder表示树的前序遍历得到的数组，inorder表示树的中序遍历得到的数组
 * - 根据这个两个数组，创建对应的二叉树，并返回树的根节点
 * 2、解题：
 * - preorder是前序遍历结果，规则是“根左右”，inorder中序遍历规则是“左根右”
 * - preorder的第一个元素，就是跟节点，该元素在inorder中也存在，则该元素位置前面的区间就是对应左子树元素
 * - 则可以通过preorder的第一个元素确定根节点元素值，再根据根节点元素值查找到在inorder中的位置，可以判断左子树的范围
 * - 其他的则是右子树范围，再根据左子树的范围继续往下遍历
 * - 一棵树的组成，就是找到根节点和对应的左右节点，
 */
fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
    var root: TreeNode? = null
    var rootV = -1
    if (preorder.isNotEmpty()) {
        rootV = preorder[0] // preorder取第一个元素，
        root = TreeNode(rootV)
    }

    if (root != null) {    // 存在
        build(root, rootV, preorder, inorder)
    }

    return root
}

/**
 * 根据传入的根节点，和前序遍历和中序遍历的数组结果，构建当前结点的左右节点，并继续往下边遍历，构成一棵完整的二叉树
 */
fun build(root: TreeNode, rootV: Int, preorder: IntArray, inorder: IntArray) {
    if (inorder.size == 1) {
        return
    }

    // 找到rootV，根元素在中序遍历inorder的位置，
    if (!inorder.contains(rootV)) {
        return
    }

    // 确定左子树根节点
    val rootIndex = inorder.indexOf(rootV)
    val leftPreOrder = preorder.copyOfRange(1, rootIndex + 1)
    if (leftPreOrder.isNotEmpty()) {
        val leftV = leftPreOrder[0]
        val leftNode = TreeNode(leftV)
        val leftInOrder = inorder.copyOfRange(0, rootIndex)
        build(leftNode, leftV, leftPreOrder, leftInOrder)
        root.left = leftNode
    }

    // 构建根节点后面的右子树-右节点
    val orderSize = preorder.size
    val rightPreOrder = preorder.copyOfRange(rootIndex + 1, orderSize)
    if (rightPreOrder.isNotEmpty()) {
        val rightV = rightPreOrder[0]
        val rightNode = TreeNode(rightV)
        val rightInOrder = inorder.copyOfRange(rootIndex + 1, orderSize)
        build(rightNode, rightV, rightPreOrder, rightInOrder)
        root.right = rightNode
    }
}

