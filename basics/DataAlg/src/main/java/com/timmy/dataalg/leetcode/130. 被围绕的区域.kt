package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print
import java.util.LinkedList

/**
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，
 * 并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 示例 1：
 * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
 * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 * 示例 2：
 * 输入：board = [["X"]]
 * 输出：[["X"]]
 *
 * 提示：
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] 为 'X' 或 'O'
 *
 * [["O","X","X","O","X"],["X","O","O","X","O"],["X","O","X","O","X"],["O","X","O","O","O"],["X","X","O","X","O"]]
 */
fun main() {

    //    val board = arrayOf(
    //        charArrayOf('X', 'X', 'X', 'X'),
    //        charArrayOf('X', 'O', 'O', 'X'),
    //        charArrayOf('X', 'X', 'O', 'X'),
    //        charArrayOf('X', 'O', 'X', 'X'),
    //    )
    val board = arrayOf(
        charArrayOf('O', 'X', 'X', 'O', 'X'),
        charArrayOf('X', 'O', 'O', 'X', 'O'),
        charArrayOf('X', 'O', 'X', 'O', 'X'),
        charArrayOf('O', 'X', 'O', 'O', 'O'),
        charArrayOf('X', 'X', 'O', 'X', 'O'),
    )
    board.print()
    solve(board)
}

/**
 * 1、审题：
 * - 输入一个m*n的矩阵，矩阵元素由字符O和X组成，找到所有由X围绕的O字符，将围绕的O替换为X
 * - 注意靠边界的O字符，因为未被围绕，不能替换为X
 * 2、解题：BSF广度优先算法 / DFS
 * - 从矩阵四周遍历，查找所有的字符O，并从边界字符开始bsf，从垂直和水平两个方向不断查找字符，
 * - 查找到是字符O及相邻的字符O，则记录下来
 * - bsf需要使用到队列，二维数组用于记录遍历过的元素，另外一个resArr数组记录字符O的位置
 * - 最终根据resArr数组，为全是O的字符，其他的部分全部替换未字符X
 * 3、总结：
 * - bfs需要使用队列保存已经遍历过的元素，并将visited标记元素设置为true标识遍历过了
 * -- 使用队列保存，
 * - dfs是不断往四周深入遍历，使用的是递归方式
 */
fun solve(board: Array<CharArray>): Unit {
    val m = board.size
    val n = board[0].size

    val visited = Array(m) {
        BooleanArray(n) { false }
    }
    val oArr = Array(m) {
        CharArray(n) { ' ' }
    }
    oArr.print()
    visited.print()
    val queue = LinkedList<IntArray>()

    // 第一行，和最后一行
    for (j in 0 until n) {
        if (board[0][j] == 'O') {
            queue.add(intArrayOf(0, j))
            visited[0][j] = true
        }
        if (board[m - 1][j] == 'O') {
            queue.add(intArrayOf(m - 1, j))
            visited[m - 1][j] = true
        }
    }

    // 第一列和最后一列
    for (i in 1 until m - 1) {
        if (board[i][0] == 'O') {
            queue.add(intArrayOf(i, 0))
            visited[i][0] = true
        }
        if (board[i][n - 1] == 'O') {
            queue.add(intArrayOf(i, n - 1))
            visited[i][n - 1] = true
        }
    }

    while (queue.isNotEmpty()) {
        val poll = queue.poll()!!

        val i = poll[0]
        val j = poll[1]
        oArr[i][j] = 'O'

        // 从[i][j]位置开始往四周扩散
        var newI = i - 1
        var newJ = j
        if (checkNewIJ(newI, newJ, m, n) && !visited[newI][newJ] && board[newI][newJ] == 'O') {
            queue.add(intArrayOf(newI, newJ))
            visited[newI][newJ] = true
        }

        newI = i + 1
        newJ = j
        if (checkNewIJ(newI, newJ, m, n) && !visited[newI][newJ] && board[newI][newJ] == 'O') {
            queue.add(intArrayOf(newI, newJ))
            visited[newI][newJ] = true
        }

        newI = i
        newJ = j + 1
        if (checkNewIJ(newI, newJ, m, n) && !visited[newI][newJ] && board[newI][newJ] == 'O') {
            queue.add(intArrayOf(newI, newJ))
            visited[newI][newJ] = true
        }

        newI = i
        newJ = j - 1
        if (checkNewIJ(newI, newJ, m, n) && !visited[newI][newJ] && board[newI][newJ] == 'O') {
            queue.add(intArrayOf(newI, newJ))
            visited[newI][newJ] = true
        }
    }

    oArr.print()
    for (i in 0 until m) {
        for (j in 0 until n) {
            if (oArr[i][j] == 'O') {
                board[i][j] = 'O'
            } else {
                board[i][j] = 'X'
            }
        }
    }
    board.print()

}

fun checkNewIJ(newI: Int, newJ: Int, m: Int, n: Int): Boolean {
    if (newI >= 0 && newI < m && newJ >= 0 && newJ < n) {
        return true
    }
    return false
}
