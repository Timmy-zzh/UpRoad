package com.timmy.dataalg

import com.timmy.dataalg.bean.ListNode
import java.util.Stack

//fun List<List<Int>>.print() {
//    print("[")
//    this.forEach { it ->
//        print("[")
//        it.forEach { item ->
//            print("$item ,")
//        }
//        print("],")
//    }
//    println("]")
//}

fun BooleanArray.print() {
    print("[")
    this.forEach {
        print("$it ,")
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

fun List<Int>.print() {
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