package com.timmy.dataalg.leetcode

import com.timmy.dataalg.bean.TreeNode
import kotlin.math.max

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
    val res = maxDepth(root)
    println("res:$res")
}

/**
 * 1、审题：
 * - 求二叉树的深度
 * 2、解题：
 * - 递归，从根节点开始，不断往下遍历，在原有基础上加上1
 * - 当节点为空时，返回深度为0
 * - 二叉树有左右两个子树，需要判断比较较大值返回
 */
fun maxDepth(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }
    val leftD = maxDepth(root.left)
    val rightD = maxDepth(root.right)
    return max(leftD, rightD) + 1
}