package com.timmy.dataalg

import com.timmy.dataalg._06tree.traversalMid
import com.timmy.dataalg._06tree.traversalPre
import com.timmy.dataalg._17dynamic.paths
import com.timmy.dataalg.bean.ListNode
import com.timmy.dataalg.bean.TreeNode
import java.util.Stack

//fun List<*>.print() {
//    print("[")
//    this.forEach {
//        print("$it,")
//    }
//    println("]")
//}
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