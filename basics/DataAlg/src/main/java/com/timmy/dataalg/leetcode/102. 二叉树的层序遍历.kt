package com.timmy.dataalg.leetcode

import com.timmy.dataalg._06tree.traversalMid
import com.timmy.dataalg.bean.TreeNode
import com.timmy.dataalg.midTraversal
import com.timmy.dataalg.print

/**
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
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
    val node20 = TreeNode(20, 15, 7)
    val root = TreeNode(3, 9, node20)
    root.midTraversal()
    val res = levelOrder(root)
    res.print()

}

/**
 * 1、审题
 * - 二叉树,层序遍历
 * 2、解题
 * - 使用链表保存遍历到的节点
 * - 但是每层使用一个集合存储,这个怎么处理好?
 * -- 问题:如何知道这一层遍历结束了,插入一个空指针进行标记
 * - 插入空节点不行,需要将链表中的所有元素都取出来,并保存到集合中
 */
fun levelOrder(root: TreeNode?): List<List<Int>> {
    val resList = arrayListOf<List<Int>>()

    val nodeList = arrayListOf<TreeNode?>()
    if (root != null)
        nodeList.add(root)

    while (nodeList.isNotEmpty()) {

        val size = nodeList.size
        var itemList = arrayListOf<Int>()
        for (i in 0 until size) {
            val node = nodeList.removeFirst()
            itemList.add(node!!.`val`)

            if (node.left != null) {
                nodeList.add(node.left)
            }
            if (node.right != null) {
                nodeList.add(node.right)
            }
        }

        resList.add(itemList)
    }

    return resList
}