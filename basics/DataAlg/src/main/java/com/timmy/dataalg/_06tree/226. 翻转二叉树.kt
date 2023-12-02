package com.timmy.dataalg._06tree

import com.timmy.dataalg.bean.TreeNode
import com.timmy.dataalg.midTraversal
import com.timmy.dataalg.print

/**
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。

示例 1：
输入：root = [4,2,7,1,3,6,9]
输出：[4,7,2,9,6,3,1]

示例 2：
输入：root = [2,1,3]
输出：[2,3,1]

示例 3：
输入：root = []
输出：[]
 */
fun main() {
    val node2 = TreeNode(2, 1, 3)
    val node7 = TreeNode(7, 6, 9)
    val root = TreeNode(4, node2, node7)

    root.midTraversal()
    invertTree(root)
    root.midTraversal()
}

/**
 * 1、审题：
 * - 输入一棵二叉树，要求翻转二叉树
 * 2、解题
 * - 从根节点开始不断往下遍历，将子节点的左右子树进行翻转
 */
fun invertTree(root: TreeNode?): TreeNode? {
    invert(root)
    return root
}

fun invert(node: TreeNode?) {
    if (node == null) {
        return
    }
    val tempNode = node.left
    node.left = node.right
    node.right = tempNode

    invert(node.left)
    invert(node.right)
}
