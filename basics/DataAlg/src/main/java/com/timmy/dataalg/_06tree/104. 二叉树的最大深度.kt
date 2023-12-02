package com.timmy.dataalg._06tree

import com.timmy.dataalg.bean.TreeNode
import com.timmy.dataalg.midTraversal
import com.timmy.dataalg.preTraversal

/**
 * 给定一个二叉树 root ，返回其最大深度。
二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。

示例 1：
输入：root = [3,9,20,null,null,15,7]
输出：3

示例 2：
输入：root = [1,null,2]
输出：2

 */
fun main() {
    val node20 = TreeNode(20, 15, 7)
    val root = TreeNode(3, 9, node20)

    root.midTraversal()
    val maxDepth = maxDepth(root)
    println("depth:$maxDepth")
    root.midTraversal()
}

/**
 * 1、审题：
 * - 输入一棵二叉树，求这棵二叉树的最大深度
 * 2、解题：
 * - 求深度，使用dfs的思想，从根节点不断往下遍历到叶子结点，遍历的过程中，深度值不断增加
 */
var maxDeep = Int.MIN_VALUE
fun maxDepth(root: TreeNode?): Int {
    maxDepthReal(root, 0)
    return maxDeep
}

fun maxDepthReal(root: TreeNode?, i: Int) {
    println("root:${root?.`val`},i=$i")
    if (root == null) {
        if (i > maxDeep) {
            maxDeep = i
        }
        return
    }
    maxDepthReal(root.left, i + 1)
    maxDepthReal(root.right, i + 1)
}
