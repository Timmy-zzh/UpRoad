package com.timmy.dataalg.leetcode

import com.timmy.dataalg.bean.TreeNode
import java.lang.StringBuilder
import java.util.LinkedList

/**
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。

示例 1：
输入：root = [1,2,3,null,null,4,5]
输出：[1,2,3,null,null,4,5]

示例 2：
输入：root = []
输出：[]

示例 3：
输入：root = [1]
输出：[1]

示例 4：
输入：root = [1,2]
输出：[1,2]

提示：
树中结点数在范围 [0, 104] 内
-1000 <= Node.val <= 1000
 */
fun main() {
}

/**
 * 1、审题：
 * - 对二叉树进行序列化和反序列化，输入一棵树，返回层序遍历的字符串，再根据输入的字符串还原回一棵树
 * 2、解题：
 * - 二叉树转成字符串，采用层序遍历，没有子节点的位置插入null，使用逗号，分隔，
 * -- 注意空节点无线循环下去，每次遇到空间点，都判断一下，队列中后面位置是否有不是空节点的，如果全是空节点，那就不用再遍历了，直接结束
 * - 反过来呢，字符串转成二叉树，先进行拆解，采用二叉树特性，
 * -- 遍历所有的结点值，然后根据 当前结点位置为i，其左节点位置为 i*2+1, 右节点位置为 i*2+2
 * 3、超出时间限制
 * - 我觉得没毛病啊，怎么就超了呢
 */
class Codec() {

    // 层序遍历，队列
    fun serialize(root: TreeNode?): String {
        val queue = LinkedList<TreeNode?>()
        if (root != null) {
            queue.add(root)
        }
        val sb = StringBuilder()
        while (queue.isNotEmpty()) {
            val node = queue.pop()
            if (node != null) {
                sb.append(node.`val`).append(",")
                queue.add(node.left)
                queue.add(node.right)
            } else {    // 当前节点为null，判断后面的结点是否有空的没
                var isAllEmpty = true
                for (i in queue.indices) {
                    if (queue[i] != null) {
                        isAllEmpty = false
                        break
                    }
                }
                if (isAllEmpty) {
                    break
                }

                sb.append("null").append(",")
                queue.add(null)
                queue.add(null)
            }
        }
        val range = sb.removeRange(sb.lastIndex - 1, sb.lastIndex)
        println("$sb ,range:$range")
        return sb.toString()
    }

    /**
     * 字符串转树结构
     * - 切割，得到字符串对应的数组
     * - 根据节点位置规则，创建树节点
     * - 对应着创建和字符串数组大小的结点数组
     */
    fun deserialize(data: String): TreeNode? {
        val stringList = data.split(",")
        if (stringList[0].isEmpty()) {
            return null
        }
        val size = stringList.size
        val nodeList = Array<TreeNode?>(stringList.size) { null }
        for (i in stringList.indices) {
            nodeList[i] = if (stringList[i].isEmpty() || stringList[i] == "null") null
            else TreeNode(stringList[i].toInt())
        }
        for (i in nodeList.indices) {
            val node = nodeList[i]
            if (node != null) {
                node.left = if (i * 2 + 1 >= size) null else nodeList[i * 2 + 1]
                node.right = if (i * 2 + 2 >= size) null else nodeList[i * 2 + 2]
            }
        }

        return nodeList[0]
    }
}




















