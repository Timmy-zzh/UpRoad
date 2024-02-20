package com.timmy.dataalg.leetcode

import com.timmy.dataalg.bean.TreeNode
import com.timmy.dataalg.levelOrder
import com.timmy.dataalg.midTraversal
import com.timmy.dataalg.preTraversal

/**
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。

示例 1：
输入：nums = [-10,-3,0,5,9]
输出：[0,-3,9,-10,null,5]
解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：

示例 2：
输入：nums = [1,3]
输出：[3,1]
解释：[1,null,3] 和 [3,1] 都是高度平衡二叉搜索树。
 */
fun main() {
//    val res = sortedArrayToBST(intArrayOf(-10,-3,0,5,9))
    val res = sortedArrayToBST(intArrayOf(1,3))
    res?.preTraversal()
    res.levelOrder()
}

/**
 * 1、审题：
 * - 输入一个升序的数组，要求根据这个数组生成一个高度平衡的二叉搜索树
 * - 特点：高度平衡， 二叉搜索树
 * 2、解题：
 * - 采用二分查找法，从数组的中间位置选中为根节点，
 * - 将数组分为三个部分，左侧区域，中间元素，和右侧区域
 * - 中间元素作为根节点，左侧区域的元素，从中选择中间元素作为左子树的左子节点，右侧区域一样的处理逻辑
 * - 最终返回这个树
 */
fun sortedArrayToBST(nums: IntArray): TreeNode? {
    if (nums.isEmpty()) {
        return null
    }
    val root = TreeNode()
    val mid = nums.size / 2
    root.`val` = nums[mid]

    val leftNode = buildTreeBin(0, mid - 1, nums)
    if (leftNode != null) {
        root.left = leftNode
    }
    val rightNode = buildTreeBin(mid + 1, nums.size - 1, nums)
    if (rightNode != null) {
        root.right = rightNode
    }
    return root
}

fun buildTreeBin(start: Int, end: Int, nums: IntArray): TreeNode? {
    if (start > end) {
        return null
    }
    val mid = start + (end - start) / 2
    val node = TreeNode()
    node.`val` = nums[mid]
    val leftNode = buildTreeBin(start, mid - 1, nums)
    if (leftNode != null) {
        node.left = leftNode
    }
    val rightNode = buildTreeBin(mid + 1, end, nums)
    if (rightNode != null) {
        node.right = rightNode
    }
    return node
}
