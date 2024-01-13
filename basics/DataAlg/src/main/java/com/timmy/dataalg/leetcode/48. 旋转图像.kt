package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 *
 * 示例 2：
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 */
fun main() {
//    val matrix = arrayOf(
//        intArrayOf(1, 2, 3),
//        intArrayOf(4, 5, 6),
//        intArrayOf(7, 8, 9)
//    )
    val matrix = arrayOf(
        intArrayOf(5, 1, 9, 11),
        intArrayOf(2, 4, 8, 10),
        intArrayOf(13, 3, 6, 7),
        intArrayOf(15, 14, 12, 16),
    )
    matrix.print()
    rotate(matrix)
    println("res:")
    matrix.print()
}


fun rotate(matrix: Array<IntArray>): Unit {
    val n = matrix.size
    for (i in 0 until n / 2) {
        for (j in 0 until (n + 1) / 2) {
            val temp = matrix[i][j]
            matrix[i][j] = matrix[n - j - 1][i]
            matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1]
            matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1]
            matrix[j][n - i - 1] = temp
        }
    }
}

/**
 * 1、审题：
 * - 输入一个n*n的数组，要求将二维数组的元素顺时针方向旋转90度，到达新的位置
 * 2、解题：
 * - 由最外层遍历到内层，逐层对元素进行旋转，每次可旋转四周的元素（start++，end--）
 * -- while（start<end)
 * - 核心：每层四周的元素旋转
 * -- temp临时变量保存
 * -- 四条边的元素进行更替，
 */
fun rotate1(matrix: Array<IntArray>): Unit {
    val n = matrix.size
    var start = 0
    var end = n - 1

    while (start < end) {
        // 每一行需要交换的元素，从第一个开始
        for (i in start until end) {  // 第0个到n-2个，因为最后一个已经在最外层交换好了
            println("start:$start,i:$i")
            //临时值
            val temp = matrix[start][i]
            // 一共有四条边，每次交换是四次
            // 把行列都用上

            // 左上角 = 左下角
            matrix[start][i] = matrix[n - 1 - i][start]

            // 左下角 = 右下角
            matrix[n - 1 - i][start] = matrix[n - 1 - i][n - 1 - i]

            // 右下角 = 右上角
            matrix[n - 1 - i][n - 1 - i] = matrix[start][n - 1 - i]

            // 右上角 = 左上角
            matrix[start][n - 1 - i] = temp

            matrix.print()
        }
        start++
        end--
    }
}








