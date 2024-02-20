package com.timmy.dataalg.leetcode

import com.timmy.dataalg.bean.Node


/**
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：

struct Node {
int val;
Node *left;
Node *right;
Node *next;
}
填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
初始状态下，所有 next 指针都被设置为 NULL。

示例 1：
输入：root = [1,2,3,4,5,6,7]
输出：[1,#,2,3,#,4,5,6,7,#]
解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，
如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。

示例 2:
输入：root = []
输出：[]

提示：
树中节点的数量在 [0, 212 - 1] 范围内
-1000 <= node.val <= 1000

进阶：
你只能使用常量级额外空间。
使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 */
fun main() {
}

/**
 * 1、审题：
 * - 输入一棵完美二叉树，完美二叉树说明树的所有叶子结点都在同一层，也可以理解为满二叉树
 * - 树中节点使用Node表示，刚开始树的结点只有左右子节点的引用，现在需要将next的引用指向节点的右侧结点
 * 2、解题：
 * - 层序遍历解法
 */
fun connect(root: Node?): Node? {
    if (root == null) {
        return null
    }
    val nodeList = arrayListOf<Node?>()
    nodeList.add(root)

    while (nodeList.isNotEmpty()) {
        val size = nodeList.size
        var preNode: Node? = null
        for (i in 0 until size) {
            val node = nodeList.removeFirst()
            if (node!!.left != null) {
                nodeList.add(node.left)
            }
            if (node.right != null) {
                nodeList.add(node.right)
            }

            if (preNode != null) {
                preNode.next = node
            }
            preNode = node
        }
    }
    return root
}