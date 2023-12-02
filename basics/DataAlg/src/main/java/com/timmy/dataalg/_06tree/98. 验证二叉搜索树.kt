package com.timmy.dataalg._06tree

import com.timmy.dataalg.bean.TreeNode
import com.timmy.dataalg.midTraversal
import com.timmy.dataalg.print

/**
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
有效 二叉搜索树定义如下：
节点的左子树只包含 小于 当前节点的数。
节点的右子树只包含 大于 当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。

示例 1：
输入：root = [2,1,3]
输出：true

示例 2：
输入：root = [5,1,4,null,null,3,6]
输出：false
解释：根节点的值是 5 ，但是右子节点的值是 4 。
 */
fun main() {
//    val root = TreeNode(2, 1, 3)

    val node4 = TreeNode(4, 3, 6)
    val root = TreeNode(5, 1, node4)

    root.midTraversal()
    val res = isValidBST(root)
    println("res=$res")
}

/**
 * 1、输入一棵二叉树，判断这棵二叉树是否是二叉搜索树
 * 2、解题：
 * - 二叉搜索树，可以采用中序遍历，找出做子树最大的节点，和右子树最小的结点，与根几点进行比较
 * - 或者通过中序遍历将所有的元素保存到一个list集合中，然后遍历这个集合，判断元素是否有序
 * 3、中序遍历，使用栈写法
 */
fun isValidBST(root: TreeNode?): Boolean {
    if (root == null) {
        return true
    }
    val list = mutableListOf<Int>()
    midTraver(root, list)
    list.print()

    var preVal = list[0]
    for (i in 1 until list.size) {
        if (preVal >= list[i]) {
            return false
        }
        preVal = list[i]
    }
    return true
}

fun midTraver(root: TreeNode?, list: MutableList<Int>) {
    if (root == null) {
        return
    }
    midTraver(root.left, list)
    list.add(root.`val`)
    midTraver(root.right, list)
}
