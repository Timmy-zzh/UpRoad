package com.timmy.dataalg.leetcode

import com.timmy.dataalg.bean.TreeNode
import kotlin.math.max

/**
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。
 * 同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
路径和 是路径中各节点值的总和。

给你一个二叉树的根节点 root ，返回其 最大路径和。

示例 1：
输入：root = [1,2,3]
输出：6
解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6

示例 2：
输入：root = [-10,9,20,null,null,15,7]
输出：42
解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42

提示：
树中节点数目范围是 [1, 3 * 104]
-1000 <= Node.val <= 1000
 */
fun main() {

    val root = TreeNode(2, 1, 3)
    val res = maxPathSum(root)
    println("res:$res")
}

/**
 * 1、审题：
 * - 输入一棵二叉树，从这棵树中寻找一条路径，路径由节点组成，求路径中节点和的最大值
 * - 要求，路径中的结点只能通过一次，路径最少包含一个结点
 * 2、解题：
 * - 回溯算法
 * - 路径是多个节点的组合，两个相邻节点有一条边，需要寻找路径中节点和的最大值，
 * - 这个路径中的结点不一定经过根节点
 * = 这道题最难的点在于路径可以是任意多个的连接结点，
 * == 每个节点有两个分叉逻辑，要么往走，要么往右，规定路径最少需要一个节点
 * - 在回溯的时候，当遍历到一个节点时的处理情况
 * -- 1、最终路径只有这一个节点的值
 * -- 2、以这个节点为源头，往左右两边遍历到的结点添加到路径中，也可以不添加，直接以该结点为源头开始
 * -- 3、
 * -- 遍历二叉树，直到叶子结点
 */

var sumRes = Int.MIN_VALUE
fun maxPathSum(root: TreeNode?): Int {
    calcPathSum(root)
    return sumRes
}

/**
 * 计算在节点node时，的最大节点值
 * - 这个节点的最终路径和，等于节点值，加上左右子树的路径和
 * - 叶子结点的路劲和为0
 * - 最终的sumRes等于
 */
fun calcPathSum(node: TreeNode?): Int {
    println("calcPathSum node:${node?.`val`}")
    if (node == null) {
        return 0
    }

    // 左子树的最大路径和
    val leftPath = max(calcPathSum(node.left), 0)
    val rightPath = max(calcPathSum(node.right), 0)

    // 那当前结点的路径和为
    val nodePathSum = node.`val` + leftPath + rightPath
    // 跟新maxRes
    sumRes = max(sumRes, nodePathSum)

    // 返回当前节点的路径和，取左右子树最的路径和
    return node.`val` + max(leftPath, rightPath)
}
