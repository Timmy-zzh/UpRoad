package com.timmy.dataalg

import com.timmy.dataalg._06tree.traversalMid
import com.timmy.dataalg._06tree.traversalPre
import com.timmy.dataalg.bean.ListNode
import com.timmy.dataalg.bean.TreeNode
import com.timmy.dataalg.leetcode.Node
import java.util.Stack

/**
 * 创建默认二维数组有固定值：
val matirx = arrayOf(
intArrayOf(1, 2, 3),
intArrayOf(4, 5, 6),
intArrayOf(7, 8, 9),
)
 */

/**
 * 创建相同值得默认二维数组
val marked = Array(m) {
BooleanArray(n) { false }
}
 */

fun Array<*>.print() {
    print("[")
    this.forEach {
        print("$it,")
    }
    println("]")
}

/**
 * 前序遍历
 */
fun TreeNode.preTraversal() {
    val list = mutableListOf<Int>()
    traversalPre(this, list)
    list.print()
}

fun TreeNode.midTraversal() {
    val list = mutableListOf<Int>()
    traversalMid(this, list)
    list.print()
}

/**
 * 层序遍历
 */
fun TreeNode?.levelOrder() {
    val resList = arrayListOf<List<Int>>()

    val nodeList = arrayListOf<TreeNode?>()
    if (this != null) nodeList.add(this)

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

    resList.print()
}

fun BooleanArray.print() {
    print("[")
    this.forEach {
        print("$it ,")
    }
    println("]")
}

fun ArrayList<IntArray>.printPath() {
    for (path in this) {
        print("${path[0]}:${path[1]}, path:${path[2]} ->")
    }
    println()
}

fun Array<CharArray>.print() {
    println("[")
    this.forEach { it ->
        print("[")
        it.forEach { item ->
            print("$item ,")
        }
        println("],")
    }
    println("]")
}

fun Array<IntArray>.print() {
    println("[")
    this.forEach { it ->
        print("[")
        it.forEach { item ->
            print("$item ,")
        }
        println("],")
    }
    println("]")
}

fun Array<BooleanArray>.print() {
    println("[")
    this.forEach { it ->
        print("[")
        it.forEach { item ->
            print("$item ,")
        }
        println("],")
    }
    println("]")
}

fun List<Any>.print() {
    this.forEach {
        print("${it.toString()} ,")
    }
    println()
}

fun List<List<String>>.printList() {
    println("[")
    this.forEach { it ->
        print("[")
        it.forEach { item ->
            print("$item ,")
        }
        println("],")
    }
    println("]")
}

fun MutableSet<Int>.print() {
    this.forEach {
        print("$it ,")
    }
    println()
}

fun IntArray.print() {
    this.forEach {
        print("$it ,")
    }
    println()
}

fun CharArray.print() {
    this.forEach {
        print("$it ,")
    }
    println()
}

fun Map<String, Int>.print() {
    this.forEach {
        println("${it.key}:${it.value},")
    }
    println()
}

fun ListNode?.print() {
    var node: ListNode? = this
    while (node != null) {
        print("${node.`val`} ,")
        node = node.next
    }
    println()
}

fun Stack<String>?.print() {
    this?.forEach {
        print("$it ,")
    }
    println()
}