package com.timmy.dataalg._06binarytree

import com.timmy.dataalg.bean.TreeNode

/**
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。

示例 1：
输入：root = [3,9,20,null,null,15,7]
输出：[[3],[9,20],[15,7]]

示例 2：
输入：root = [1]
输出：[[1]]

示例 3：
输入：root = []
输出：[]

 */
fun main() {
    val node21 = TreeNode(20, 15, 7)
    val root = TreeNode(3, TreeNode(9), node21)

    val res = levelOrder(root)

    print("[")
    res.forEach { it ->
        print("[")
        it.forEach { item ->
            print("$item ,")
        }
        print("],")
    }
    println("]")

}

/**
 * 层序遍历：
 * - 按层遍历，将父节点添加到队列中，
 * - 然后从队列中取出节点，并将节点的左右子节点添加到队列中，那什么一层的结点遍历结束呢？
 * -- 使用一个替换的队列
 * 总结：
 * - 空间复杂度挺大的
 */
fun levelOrder(root: TreeNode?): List<List<Int>> {
    val resList = mutableListOf<List<Int>>()
    var itemList = mutableListOf<TreeNode?>()
    root?.let { itemList.add(it) }

    while (itemList.isNotEmpty()) {
        // 保存每一层的临时节点集合
        val itemListTemp = mutableListOf<TreeNode?>()
        // 保存每一层的数据集合
        val itemIntList = mutableListOf<Int>()
        while (itemList.isNotEmpty()) {
            val node = itemList.removeFirst()
            node?.`val`?.let { itemIntList.add(it) }

            node?.left?.let { itemListTemp.add(it) }
            node?.right?.let { itemListTemp.add(it) }
        }
        resList.add(itemIntList)
        // 赋值
        itemList = itemListTemp
    }
    return resList
}