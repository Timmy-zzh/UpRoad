package com.timmy.dataalg._06tree

import com.timmy.dataalg.bean.TreeNode
import com.timmy.dataalg.midTraversal

/**
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，
 * 这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
叶子节点 是指没有子节点的节点。

示例 1：
输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
输出：true
解释：等于目标和的根节点到叶节点路径如上图所示。

示例 2：
输入：root = [1,2,3], targetSum = 5
输出：false
解释：树中存在两条根节点到叶子节点的路径：
(1 --> 2): 和为 3
(1 --> 3): 和为 4
不存在 sum = 5 的根节点到叶子节点的路径。

示例 3：
输入：root = [], targetSum = 0
输出：false
解释：由于树是空的，所以不存在根节点到叶子节点的路径。
 */
fun main() {

    //    val node11 = TreeNode(11, 7, 2)
    //    val node41 = TreeNode(4, node11, null)
    //
    //    val node4 = TreeNode(4, null, TreeNode(1))
    //    val node8 = TreeNode(8, 13, node4)
    //    val root = TreeNode(5, node41, node8)
    //
    //    root.midTraversal()
    //
    //    val res = hasPathSum(root, 22)

    //    val root = TreeNode(1, 2, 3)
    //    val res = hasPathSum(root, 5)

    //    val res = hasPathSum(null, 0)

    val root = TreeNode(1)
    root.right = TreeNode(2)
    val res = hasPathSum(root, 1)
    println("res=$res")
}

/**
 * 1、审题：
 * - 输入一棵二叉树和目标值，从根节点遍历到叶子结点，如果目标值相同则结束遍历
 * 2、解题
 * - 回溯算法思想
 */
fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
    if (root == null) {
        return false
    }
    if (root.left != null && root.right != null) {
        return hasPathSumReal(root, targetSum)
    }
    if (root.left != null) {
        return hasPathSumReal(root.left, targetSum - root.`val`)
    }
    if (root.right != null) {
        return hasPathSumReal(root.right, targetSum - root.`val`)
    }
    return hasPathSumReal(root, targetSum)
}

fun hasPathSumReal(root: TreeNode?, targetSum: Int): Boolean {
    println("node：${root?.`val`},tarsum=$targetSum")
    if (root == null) {
        if (targetSum == 0) {
            return true
        }
        return false
    }
    return hasPathSumReal(root.left, targetSum - root.`val`) || hasPathSumReal(root.right,
        targetSum - root.`val`)
}
