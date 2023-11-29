package com.timmy.dataalg._17dynamic

import com.timmy.dataalg.print

fun main() {
    paths.add(intArrayOf(0, 0)) //    minDistV1(0, 0, matrix[0][0])
    //    minDistV2(0, 0, matrix[0][0])
    minDistV3()
    println("minDis:$minDis")
}

fun printPath() {
    for (path in paths) {
        print("${path[0]}:${path[1]} ->")
    }
    println()
}

/**
 * 审题：有一个矩阵，每个位置都有一个元素值，表示到达该位置需要经过的路程长度
 * - 没有元素只能往右，或者往下两个方向移动，求从矩阵[0,0]位置触发，到右下角位置的最短路径长度
 * 解题：回溯法实现
 * - 从[0,0]位置开始，不断递归调用，往下和往右遍历，当到达右下角时，取出最小路径值
 */
val matrix = arrayOf(
    intArrayOf(1, 3, 5, 9),
    intArrayOf(2, 1, 3, 4),
    intArrayOf(5, 2, 6, 7),
    intArrayOf(6, 8, 4, 3),
)
var minDis = Int.MAX_VALUE
const val row = 4
const val column = 4
val paths = ArrayList<IntArray>()

fun minDistV1(i: Int, j: Int, dis: Int) {
    println("i=$i,j=$j, dis=$dis")
    if (i == row - 1 && j == column - 1) {
        if (dis < minDis) {
            minDis = dis
        }
        return
    }

    // 往下
    if (i + 1 < row) {
        minDistV1(i + 1, j, dis + matrix[i + 1][j])
    }

    // 向右
    if (j + 1 < column) {
        minDistV1(i, j + 1, dis + matrix[i][j + 1])
    }
}

/**
 * 增加路径记录和打印
 */
fun minDistV2(i: Int, j: Int, dis: Int) {
    if (i == row - 1 && j == column - 1) {
        if (dis < minDis) {
            minDis = dis
        }
        println("i=$i,j=$j, dis=$dis")
        printPath()
        return
    }

    // 往下
    if (i + 1 < row) {
        val intArrayOf = intArrayOf(i + 1, j)
        paths.add(intArrayOf)
        minDistV2(i + 1, j, dis + matrix[i + 1][j])
        paths.remove(intArrayOf)
    }

    // 向右
    if (j + 1 < column) {
        val intArrayOf = intArrayOf(i, j + 1)
        paths.add(intArrayOf)
        minDistV2(i, j + 1, dis + matrix[i][j + 1])
        paths.remove(intArrayOf)
    }
}

/**
 * 动态规划解法
 * - 问题是否能分阶段求的最优解？
 * - 状态转移方程式：
 * -- paths[i][j] = matrix[i][j] + min(paths[i-1][j] ， paths[i][j-1])
 * - 先把第一行，和第一列的状态值求出来，
 * - 再来推导中间部分的状态值
 */
fun minDistV3() {
    val status = Array(4) {
        IntArray(4) { -1 }
    }
    status[0][0] = matrix[0][0]

    // 第一列
    for (i in 1 until 4) {
        status[i][0] = matrix[i][0] + status[i - 1][0]
    }

    // 第一行
    for (j in 1 until 4) {
        status[0][j] = matrix[0][j] + status[0][j - 1]
    }

    status.print()

    for (i in 1 until 4) {
        for (j in 1 until 4) {
            status[i][j] = matrix[i][j] + status[i - 1][j].coerceAtMost(status[i][j - 1])
        }
    }
    println("res=${status[3][3]}")

    status.print()
}