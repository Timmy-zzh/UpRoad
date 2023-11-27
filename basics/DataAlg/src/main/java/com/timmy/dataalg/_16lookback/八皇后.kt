package com.timmy.dataalg._16lookback

import com.timmy.dataalg.print

fun main() {
    cal8Queue(0)
}

/**
 * 八皇后问题：
 * -回溯算法思想
 * - 在一个八行八列的棋盘上，没一行都有一个皇后，这个皇后的脾气不好
 * - 在同一行，同一列，和两条对角线上，都只能有一条对角线，否则这一行的皇后放大其他位置上
 * 解题：
 * - 使用一个8位的数组保存每一行皇后所在的位置
 * - 按照行，来进行排查皇后已存在的位置
 * - 不断递归调用cal8Queue方法，每行的皇后，都可以在8个位置上进行排列，每行都有八个选择，然后判断当前所在的位置是否ok
 * - 其实只要判断当前位置的，左上角，上面，和右上角是否已经存在了皇后？本行不用排查
 *
 * 总结：
 * - 创建一个只有8个元素大小的int数组
 *  //val queues = arrayOfNulls<Int>(8)
 * - 从第一行row=0开始遍历，皇后存放的位置
 */
val queues = IntArray(8)
fun cal8Queue(row: Int) { //    println("cal8Queue row:$row")
    if (row == 8) { // 第九行了，打印结果
        printQueue()
        return
    }
    for (i in 0 until 8) { // 列
        if (checkQ(row, i)) {
            queues[row] = i
            cal8Queue(row + 1)
        }
    }
}

/**
 * row:行
 * column：列
 */
fun checkQ(row: Int, column: Int): Boolean { //    println("chechQ row:$row, column:$column")
    val size = queues.size
    var leftUp = column - 1
    var rightUp = column + 1
    for (i in row - 1 downTo 0) { // 不断往上遍历行

        // 上面的行，皇后位置是否有在正上方的column
        if (queues[i] == column) {
            return false
        }

        // 左上角，i为行不断减少，leftup为列不断--
        if (leftUp >= 0 && queues[i] == leftUp) {
            return false
        }
        leftUp--

        // 右上角，i为行减少，列为rightup不断增加
        if (rightUp < size && queues[i] == rightUp) {
            return false
        }
        rightUp++
    }
    return true
}

fun printQueue() {
    queues.print()
    val size = queues.size
    for (i in 0 until size) {
        for (j in 0 until size) {
            if (queues[i] == j) {   // 第i行的皇后位置，在第j列
                print("Q ")
            } else {
                print("* ")
            }
        }
        println()
    }
    println()
}
