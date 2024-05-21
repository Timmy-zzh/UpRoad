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
 * 1、二叉树的序列化与反序列化
 * 2、之前的解法，当遇到链表二叉树时，对于所有的空节点都添加了空节点标记null，这样在分割后，会导致数据数量过大
 * - 解决：序列化时，采用深度优先遍历，前序遍历方式-根左右，遇到空节点只保存一层空叶子节点
 * - 反序列化时，创建二叉树，不断从集合中获取值，并添加到树中的结点，
 * = 核心是递归算法，和创建二叉树的方式要想清楚
 * 3、总结：
 * - 二叉树的遍历方式，层序遍历是广度优先遍历，前序中序后续遍历是深度优先算法
 * - 二叉树的创建，根据前序深度优先遍历，不断创建节点，并产生关联
 */
class Codec() {
    fun serialize(root: TreeNode?): String {
        val res = reSerialize(root, "")
        println("res:$res")
        return res
    }

    private fun reSerialize(root: TreeNode?, str: String): String {
        var res = str
        if (root == null) {
            res += "None,"
            return res
        }

        res += (root.`val`.toString() + ",")
        res = reSerialize(root.left, res)
        res = reSerialize(root.right, res)

        return res
    }

    /**
     * 反序列化：
     * - 先分割，逗号，
     * - 创建二叉树
     */
    fun deserialize(data: String): TreeNode? {
        val stringList = data.split(",")
        val dataList = mutableListOf<String>()
        dataList.addAll(stringList)
        if (stringList.isEmpty()) {
            return null
        }
        return reDeserialize(dataList)
    }

    private fun reDeserialize(dataList: MutableList<String>): TreeNode? {
        if (dataList[0] == "None") {
            dataList.removeAt(0)
            return null
        }

        val node = TreeNode(dataList[0].toInt())
        dataList.removeAt(0)
        node.left = reDeserialize(dataList)
        node.right = reDeserialize(dataList)

        return node
    }
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
 * - 序列化过程中，获取到一个节点为空时，需要判断该结点后面是否还有后续节点，如果有后续节点，则插入一个null空节点的元素
 * - 如果没有后续节点，则直接结束该遍历，否则没有终止条件出现
 * = 问题就出在这里，当有大量的二叉树节点时，可能插入了很多空节点了，在遍历后续是否还有节点时，会多次重复遍历，导致出现时间超时
 * - 解决：使用一个变量pos，标记当前添加非空节点的位置，从队列中取出一个元素，则pos减少1，当遍历到空节点时，判断当前pos是否大于0，
 * -- 如果大于0说明，还有非空节点，否则全是空节点直接break
 * 4、超出内存限制
 * - 那就是反序列化中，通过逗号,分割出数组对象超过内存限制了
 */
class Codec1() {

    // 层序遍历，队列
    fun serialize(root: TreeNode?): String {
        val queue = LinkedList<TreeNode?>()
        var pos = 0
        if (root != null) {
            queue.add(root)
            pos++
        }
        val sb = StringBuilder()
        while (queue.isNotEmpty()) {
            val node = queue.pop()
            pos--
            if (node != null) {
                sb.append(node.`val`).append(",")
                queue.add(node.left)
                queue.add(node.right)
                if (node.left != null || node.right != null) {
                    pos = queue.size
                }
            } else { // 当前节点为null，判断后面的结点是否有空的没
                if (pos <= 0) {
                    break
                }

                sb.append("null").append(",")
                queue.add(null)
                queue.add(null)
            }
        }
        return sb.toString()
    }

    /**
     * 反序列化，对输入的字符串，根据逗号，进行裁剪，在组成二叉树时，使用递归的方式求解
     * - 如果使用两个数组，就可能出现超出内存限制的问题
     */
    fun deserialize(data: String): TreeNode? {
        println("deserialize data: $data")
        val stringList = data.split(",")
        if (stringList[0].isEmpty()) {
            return null
        }
        val root = TreeNode(stringList[0].toInt())
        deserializeRecur(root, 0, stringList, stringList.size)

        return root
    }

    private fun deserializeRecur(node: TreeNode, i: Int, stringList: List<String>, size: Int) {

        // 左子节点
        val leftP = i * 2 + 1
        if (leftP >= size || stringList[leftP].isEmpty() || stringList[leftP] == "null") {
            println("leftP null")
        } else {
            val leftNode = TreeNode(stringList[leftP].toInt())
            node.left = leftNode
            deserializeRecur(leftNode, leftP, stringList, size)
        }

        // 右子节点
        val rightP = i * 2 + 2
        if (rightP >= size || stringList[rightP].isEmpty() || stringList[rightP] == "null") {
            println("rightP null")
        } else {
            val rightNode = TreeNode(stringList[rightP].toInt())
            node.right = rightNode
            deserializeRecur(rightNode, rightP, stringList, size)
        }
    }

    // 层序遍历，队列
    fun serialize1(root: TreeNode?): String {
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
            } else { // 当前节点为null，判断后面的结点是否有空的没
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
        return sb.toString()
    }

    /**
     * 字符串转树结构
     * - 切割，得到字符串对应的数组
     * - 根据节点位置规则，创建树节点
     * - 对应着创建和字符串数组大小的结点数组
     */
    fun deserialize1(data: String): TreeNode? {
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




















