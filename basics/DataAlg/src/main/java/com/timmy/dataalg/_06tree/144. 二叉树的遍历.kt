package com.timmy.dataalg._06tree

import com.timmy.dataalg.bean.TreeNode
import com.timmy.dataalg.print

/**
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。

示例 1：
输入：root = [1,null,2,3]
输出：[1,2,3]

示例 2：
输入：root = []
输出：[]

示例 3：
输入：root = [1]
输出：[1]

示例 4：
输入：root = [1,2]
输出：[1,2]

示例 5：
输入：root = [1,null,2]
输出：[1,2]
 */
fun main() {
    val root = TreeNode(1)
    val node2 = TreeNode(2)
    val node3 = TreeNode(3)

    root.right = node2
    node2.left = node3

    val res = preorderTraversal(root)
    res.print()
}

/**
 * 二叉树的前序遍历：
 * - 先父节点，再左子树，最后右子树
 */
fun preorderTraversal(root: TreeNode?): List<Int> {
    val list = mutableListOf<Int>()
//    traversalPre(root, list)
//    traversalMid(root, list)
    traversalNext(root, list)
    return list
}

/**
 * 前序遍历
 */
fun traversalPre(node: TreeNode?, list: MutableList<Int>) {
    if (node == null) {
        return
    }
    list.add(node.`val`)
    traversalPre(node.left, list)
    traversalPre(node.right, list)
}

/**
 * 中序遍历
 */
fun traversalMid(node: TreeNode?, list: MutableList<Int>) {
    if (node == null) {
        return
    }
    traversalMid(node.left, list)
    list.add(node.`val`)
    traversalMid(node.right, list)
}

/**
 * 后序遍历
 */
fun traversalNext(node: TreeNode?, list: MutableList<Int>) {
    if (node == null) {
        return
    }
    traversalNext(node.left, list)
    traversalNext(node.right, list)
    list.add(node.`val`)
}



















