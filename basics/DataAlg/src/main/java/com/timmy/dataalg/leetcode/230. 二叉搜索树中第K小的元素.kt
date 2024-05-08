package com.timmy.dataalg.leetcode

import com.timmy.dataalg._06tree.midTraver
import com.timmy.dataalg.bean.TreeNode

/**
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。

示例 1：
输入：root = [3,1,4,null,2], k = 1
输出：1

示例 2：
输入：root = [5,3,6,2,4,null,null,1], k = 3
输出：3

提示：
树中的节点数为 n 。
1 <= k <= n <= 104
0 <= Node.val <= 104
进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
 */
fun main() {
}


//fun kthSmallest(root: TreeNode?, k: Int): Int {
//    return midTraverSelf2(root, k, 1)
//}
//
//fun midTraverSelf2(root: TreeNode?, k: Int, i: Int): Int {
//    if (k == i) {
//        return root!!.`val`
//    }
//}

/**
 * 1、审题：
 * - 输入一个二叉搜素树，求这棵树中第k小的元素
 * 2、解题
 * - 因为二叉搜素树的特点是，左子树比根节点值小，右子树比根节点都大
 * - 采用中序遍历，将所有元素添加到集合中，返回集合中的第k个
 * - 或者在中序遍历的过程中就直接返回
 * -- 中序遍历有递归算法，和迭代解法
 */
fun kthSmallest2(root: TreeNode?, k: Int): Int {
    val list = mutableListOf<Int>()
    midTraverSelf(root, list)
    return list[k - 1]
}

fun midTraverSelf(root: TreeNode?, list: MutableList<Int>) {
    if (root == null) {
        return
    } // 中序遍历，左根右
    midTraverSelf(root.left, list)
    list.add(root.`val`)
    midTraverSelf(root.right, list)
}
