package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。

示例 1：
输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
输出：[[1,0,1],[0,0,0],[1,0,1]]

示例 2：
输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 */
fun main() {
    //    val matrix = arrayOf(
    //        intArrayOf(1, 1, 1),
    //        intArrayOf(1, 0, 1),
    //        intArrayOf(1, 1, 1),
    //    )
    val matrix = arrayOf(
        intArrayOf(0, 1, 2, 0),
        intArrayOf(3, 4, 5, 2),
        intArrayOf(1, 3, 1, 5),
    )
    matrix.print()
    setZeroes(matrix)
    matrix.print()
}

/**
 * 1、审题：
 * - 输入一个二维数组，数组元素由0和整数组成，如果一个元素位置出现了元素0，则需要将该元素的行和列都设置为0
 * - 要求原地算法，意思是不能新创建数组,还是说不能借助其他创建的另外的对象吗
 * 2、解题
 * - 首先需要找到所有的元素为0的位置，将该元素位置的行和列保存下来
 * - 再进行二次遍历，发现命中了保存的行或者列，则设置为0
 * 3、总结：
 * - 先找到0的位置，进行标记保存，最后再遍历设置为0
 */
fun setZeroes(matrix: Array<IntArray>): Unit {
    val rows = mutableListOf<Int>() // 行集合
    val columns = mutableListOf<Int>()
    val m = matrix.size
    val n = matrix[0].size
    for (i in 0 until m) {
        for (j in 0 until n) {
            if (matrix[i][j] == 0) {
                if (!rows.contains(i)) {
                    rows.add(i)
                }
                if (!columns.contains(j)) {
                    columns.add(j)
                }
            }
        }
    }

    for (i in 0 until m) {
        for (j in 0 until n) {
            if ((rows.contains(i) || columns.contains(j)) && matrix[i][j] != 0) {
                matrix[i][j] = 0
            }
        }
    }
}