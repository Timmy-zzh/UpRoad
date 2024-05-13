package com.timmy.dataalg.leetcode

import java.util.LinkedList
import kotlin.math.max

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
    /**
     * [[5,8,11,11,12,12,14,14,19],
     * [9,9,14,17,17,19,21,26,27],
     * [12,15,15,21,21,26,30,35,36],
     * [17,17,20,25,28,30,32,35,39],
     * [20,21,22,29,30,32,34,36,43],
     * [24,24,25,31,36,40,40,43,45],
     * [28,31,32,36,36,45,49,53,56],
     * [29,33,36,39,40,50,54,57,57],
     * [34,36,37,41,42,53,55,58,59],
     * [37,40,42,44,47,53,56,58,62]]
     */

    val matrix1 = arrayOf(
        intArrayOf(5, 8, 11, 11, 12, 12, 14, 14, 19),
        intArrayOf(9, 9, 14, 17, 17, 19, 21, 26, 27),
        intArrayOf(12, 15, 15, 21, 21, 26, 30, 35, 36),
        intArrayOf(17, 17, 20, 25, 28, 30, 32, 35, 39),
        intArrayOf(20, 21, 22, 29, 30, 32, 34, 36, 43),
        intArrayOf(24, 24, 25, 31, 36, 40, 40, 43, 45),
        intArrayOf(28, 31, 32, 36, 36, 45, 49, 53, 56),
        intArrayOf(29, 33, 36, 39, 40, 50, 54, 57, 57),
        intArrayOf(34, 36, 37, 41, 42, 53, 55, 58, 59),
        intArrayOf(37, 40, 42, 44, 47, 53, 56, 58, 62),
    )
    val res = searchMatrix(matrix1, 22)
    println("res:$res")
}

/**
 * 用二分法解决才行
 * - 不断缩小范围，沿着对角线不断缩小范围，遇到边界问题，则沿着边界移动，没错，就这思路
 * - 不断沿着对角线缩短检索范围，最终一定是在一个2*n的区间范围，进行最后的检索，只需要判断这个区间中是否有目标值
 * 3、总结
 * - 疯了疯了，改了多次，总是差一点，就压放弃了
 * - 还好最后解答出来了，真实一段煎熬的时期
 * - 再看官方题解，还有更简单的解法，每行作为一个整体来进行二分查找，666啊
 */
fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
    val row = matrix.size // 行
    val col = matrix[0].size // 列
    println("row:$row, col:$col")

    var minRow = 0  // 左上角最小值的点
    var minCol = 0

    var maxRow = row - 1 // 右下角最大值的点
    var maxCol = col - 1
    if (matrix[minRow][minCol] == target || matrix[maxRow][maxCol] == target) {
        return true
    }

    while (minRow <= maxRow && minCol <= maxCol) { // 中间的值
        var midRow = minRow + (maxRow - minRow) / 2
        var midCol = minCol + (maxCol - minCol) / 2

        println("mid :[$midRow][$midCol]")
        if (matrix[midRow][midCol] == target) {
            return true
        }
        if (midRow + 1 >= row || midCol + 1 >= col || midRow - 1 < 0 || midCol - 1 < 0) {
            break
        }

        if (matrix[midRow + 1][midCol + 1] < target) {
            minRow = midRow + 1
            minCol = midCol + 1
        } else if (matrix[midRow - 1][midCol - 1] > target) {
            maxRow = midRow - 1
            maxCol = midCol - 1
        } else {
            break
        }
    }

    // 最小~最大 区间遍历
    println("[$minRow][$minCol]")
    println("[$maxRow][$maxCol]")

    /**
     * 找到最小~最大 区间遍历
     * - 说明左上角的区域都是小于目标值的，右下角的区域都是大于目标值的，都不是应该检索的区域
     * - 需要从左下角，和右上角两个区域进行查找
     */

    // 左下角
    for (i in minRow until row) {
        for (j in 0 until maxCol) {
            if (matrix[i][j] == target) {
                return true
            }
        }
    }

    // 右上角
    for (i in 0 until maxRow) {
        for (j in minCol until col) {
            if (matrix[i][j] == target) {
                return true
            }
        }
    }

    return false
}

/**
 * 1、审题：
 * - 输入一个二维数组，和一个目标值，在二维数组中搜索是否存在该目标值
 * - 二维数组有个特点，行从左到右为升序，列从上到下也是升序
 * 2、解题：
 * - bfs解法，使用队列保存所有比目标值的元素位置，直到找到目标值，或者队列为空
 * -- bfs解法-> 超出时间限制
 * - dfs + 回溯解法，使用visited二维数组标记已遍历过的元素，不断往深处遍历查找
 * - 二分法解法，
 */
fun searchMatrixBfs(matrix: Array<IntArray>, target: Int): Boolean {
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