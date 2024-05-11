package com.timmy.dataalg.leetcode

import java.util.LinkedList

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
每行的元素从左到右升序排列。
每列的元素从上到下升序排列。

示例 1：
输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
输出：true

示例 2：
输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
输出：false

提示：
m == matrix.length
n == matrix[i].length
1 <= n, m <= 300
-109 <= matrix[i][j] <= 109
每行的所有元素从左到右升序排列
每列的所有元素从上到下升序排列
-109 <= target <= 109
 */
fun main() {

}

/**
 * 用二分法解决才行
 * - 不断缩小范围，沿着对角线不断缩小范围，遇到边界问题，则沿着边界移动
 */
fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {

    return false
}

/**
 * 1、审题：
 * - 输入一个二维数组，和一个目标值，在二维数组中搜索是否存在该目标值
 * - 二维数组有个特点，行从左到右为升序，列从上到下也是升序
 * 2、解题：
 * - bfs解法，使用队列保存所有比目标值的元素位置，直到找到目标值，或者队列为空
 * - dfs + 回溯解法，使用visited二维数组标记已遍历过的元素，不断往深处遍历查找
 * - 二分法解法，
 */
fun searchMatrixBfs(matrix: Array<IntArray>, target: Int): Boolean { // bfs解法-> 超出时间限制
    val queue = LinkedList<IntArray>()
    val row = matrix.size
    val col = matrix[0].size

    if (matrix[0][0] <= target) {
        queue.add(intArrayOf(0, 0))
    }

    while (queue.isNotEmpty()) {
        val ints = queue.pop()
        val i = ints[0]
        val j = ints[1]

        if (matrix[i][j] == target) {
            return true
        }

        if (j + 1 < col && matrix[i][j + 1] <= target) {  // 右侧元素
            queue.add(intArrayOf(i, j + 1))
        }

        if (i + 1 < row && matrix[i + 1][j] <= target) {  // 下方元素
            queue.add(intArrayOf(i + 1, j))
        }
    }

    return false
}