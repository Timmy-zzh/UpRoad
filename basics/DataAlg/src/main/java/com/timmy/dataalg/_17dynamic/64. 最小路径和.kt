package com.timmy.dataalg._17dynamic

import com.timmy.dataalg.print
import com.timmy.dataalg.printPath

/**
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
说明：每次只能向下或者向右移动一步。

示例 1：
输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
输出：7
解释：因为路径 1→3→1→1→1 的总和最小。

示例 2：
输入：grid = [[1,2,3],[4,5,6]]
输出：12
 */
fun main() {
    val grid = arrayOf(
        intArrayOf(1, 3, 1),
        intArrayOf(1, 5, 1),
        intArrayOf(4, 2, 1),
    )

    //    val grid = arrayOf( //        intArrayOf(1, 2, 3) //        intArrayOf(4, 5, 6) //    )
    grid.print()
    val res = minPathSum(grid)
    println("res:$res")
}

//val paths64 = ArrayList<IntArray>()

/**
 * 1、审题：
 * - 输入一个矩阵，求从左上角到右下角的最短距离
 * - 每次可以路径可以往下，或者往右移动
 * 2、解法：回溯算法
 */ //var minPath = Int.MAX_VALUE
//fun minPathSum(grid: Array<IntArray>): Int {
//    val m = grid.size
//    val n = grid[0].size
//
//    println("grid[0][0]=${grid[0][0]}")
//    val intArrayOf = intArrayOf(0, 0, grid[0][0])
//    paths64.add(intArrayOf)
//    minPathFun(0, 0, m, n, grid, grid[0][0])
//    paths64.remove(intArrayOf)
//    return minPath
//}
//
//fun minPathFun(i: Int, j: Int, m: Int, n: Int, grid: Array<IntArray>, pathSum: Int) {
//    println("i=$i,j=$j,  m=$m,n=$n, pathSum=$pathSum")
//    if (i == m - 1 && j == n - 1) {
//        if (pathSum < minPath) {
//            minPath = pathSum
//        }
//        paths64.printPath()
//        return
//    }
//
//    // 往下移动
//    if (i + 1 < m) {
//        val intArrayOf = intArrayOf(i + 1, j, pathSum + grid[i + 1][j])
//        paths64.add(intArrayOf)
//        minPathFun(i + 1, j, m, n, grid, pathSum + grid[i + 1][j])
//        paths64.remove(intArrayOf)
//    }
//
//    // 往右移动
//    if (j + 1 < n) {
//        val intArrayOf = intArrayOf(i, j + 1, pathSum + grid[i][j + 1])
//        paths64.add(intArrayOf)
//        minPathFun(i, j + 1, m, n, grid, pathSum + grid[i][j + 1])
//        paths64.remove(intArrayOf)
//    }
//}

var minPath = Int.MAX_VALUE
fun minPathSumBack(grid: Array<IntArray>): Int {
    val m = grid.size
    val n = grid[0].size
    minPathFun(0, 0, m, n, grid, grid[0][0])
    return minPath
}

fun minPathFun(i: Int, j: Int, m: Int, n: Int, grid: Array<IntArray>, pathSum: Int) {
    println("i=$i,j=$j,  m=$m,n=$n, pathSum=$pathSum")
    if (i == m - 1 && j == n - 1) {
        if (pathSum < minPath) {
            minPath = pathSum
        }
        return
    }

    // 往下移动
    if (i + 1 < m) {
        minPathFun(i + 1, j, m, n, grid, pathSum + grid[i + 1][j])
    }

    // 往右移动
    if (j + 1 < n) {
        minPathFun(i, j + 1, m, n, grid, pathSum + grid[i][j + 1])
    }
}

/**
 * 动态规划解法
 * - 分阶段求解最优解
 * - 使用状态值保存每个阶段的结果，先将第一列和第一行的数据处理
 * - 接着遍历剩下的状态值，从左边和上面的状态值取最小值，再加上当前路径值
 */
fun minPathSum(grid: Array<IntArray>): Int {
    val m = grid.size
    val n = grid[0].size
    val paths = Array(m) {
        IntArray(n) { 0 }
    }
    paths[0][0] = grid[0][0]
    for (i in 1 until m) {  // 第一列
        paths[i][0] = paths[i - 1][0] + grid[i][0]
    }
    for (j in 1 until n) {  // 第一行
        paths[0][j] = paths[0][j - 1] + grid[0][j]
    }

    for (i in 1 until m) {
        for (j in 1 until n) {
            paths[i][j] = grid[i][j] + paths[i][j - 1].coerceAtMost(paths[i - 1][j])
        }
    }

    return paths[m - 1][n - 1]
}



