package com.timmy.dataalg.leetcode

import com.timmy.dataalg.bean.TreeNode
import com.timmy.dataalg.midTraversal
import com.timmy.dataalg.preTraversal
import com.timmy.dataalg.print
import java.util.LinkedList
import java.util.Queue

/**
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[20,9],[15,7]]
 *
 * 示例 2：
 * 输入：root = [1]
 * 输出：[[1]]
 *
 * 示例 3：
 * 输入：root = []
 * 输出：[]
 */
fun main() {
//    val node20 = TreeNode(20, 15, 7)
//    val root = TreeNode(3, 9, node20)

    val node3 = TreeNode(3)
    node3.right = TreeNode(5)

    val node2 = TreeNode(2)
    node2.left = TreeNode(4)

    val root = TreeNode(1, node2, node3)

    root.preTraversal()
    val res = zigzagLevelOrder(root)
    res.print()
}

/**
 * 1、审题
 * - 锯齿形层序遍历
 * 2、解题
 * - 先从左遍历,接着从左往右遍历
 * - 增加一个标记位控制
 */
fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
    val resList = arrayListOf<List<Int>>()

    val nodeList = arrayListOf<TreeNode?>()
    if (root != null) {
        nodeList.add(root)
    }
    var sign = 0

    while (nodeList.isNotEmpty()) {

        val size = nodeList.size
        var itemList = LinkedList<Int>()

        for (i in 0 until size) {
            val node = nodeList.removeFirst()
            if (sign % 2 == 0) {
                itemList.offerFirst(node!!.`val`)
            } else {
                itemList.offerLast(node!!.`val`)
            }

            if (node.left != null) {
                nodeList.add(node.left)
            }
            if (node.right != null) {
                nodeList.add(node.right)
            }
        }

        sign++
        resList.add(itemList)
    }

    return resList
}