package com.timmy.dataalg.leetcode

import com.timmy.dataalg.bean.TreeNode

/**
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 *
 * 示例 1：
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 */
fun main() {
    val left2 = TreeNode(2, 3, 4)
    val right2 = TreeNode(2, 4, 3)
    val root = TreeNode(1, left2, right2)
    val res = isSymmetric(root)
    println("res:$res")

}

/**
 * 1、审题:
 * - 输入一颗树,判断这颗树是否沿中心轴对称
 * - 镜像对称,从根节点开始,他的左子节点与右子节点的值相等,
 * 2、解题:
 * - 从根节点的左右两个子树开始,传入树中的两个节点,判断这两个节点的值是否相等
 * - 继续判断左右节点的下一层,判断规则是左子树的左节点,与右子树的右节点;
 * - 与左子树的右节点,与右子树的左节点,两个继续进行下一轮的判断
 */
fun isSymmetric(root: TreeNode?): Boolean {
    if (root == null) {
        return true
    }
    return isSymmetricReal(root.left, root.right)
}

fun isSymmetricReal(left: TreeNode?, right: TreeNode?): Boolean {
    println("isSymmetricReal left:${left?.`val`},right:${right?.`val`}")
    if (left == right && left == null) {    // 两个都为空
        return true
    }
    if (left == null || right == null) {    // 其中一个为空
        return false
    }
    // 两个都不为空,但不想等
    if (left.`val` != right.`val`) {
        return false
    }

    // 下一层,左子树的左节点,与右子树的右节点
    val leftRes = isSymmetricReal(left.left, right.right)
    val rightRes = isSymmetricReal(left.right, right.left)
    return leftRes && rightRes
}
