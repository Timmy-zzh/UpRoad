package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

示例 1：
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
输出：true

示例 2：
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
输出：true

示例 3：
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
输出：false
 */
fun main() {

    //        val board = arrayOf(
    //            charArrayOf('A', 'B', 'C', 'E'),
    //            charArrayOf('S', 'F', 'C', 'S'),
    //            charArrayOf('A', 'D', 'E', 'E'),
    //        )
    //        val res = exist(board, "SEE")

    val board = arrayOf(
        charArrayOf('A', 'B', 'C', 'E'),
        charArrayOf('S', 'F', 'E', 'S'),
        charArrayOf('A', 'D', 'E', 'E'),
    )
    val res = exist(board, "ABCESEEEFS")
    println("res:$res")

    //    val board = arrayOf(
    //        charArrayOf('A', 'A'),
    //    )
    //    val res = exist(board, "AA")
    //    println("res:$res")
}

/**
 * 1、审题
 * - 输入一个二维数组和一个字符串，从二维数组中的某个元素开始，在水平和垂直方向相邻的元素，开始dfs深度优先遍历
 * - 直到找到和world相同的子集
 * 2、解题: DFS深度优先算法遍历查找
 * - dfs算法，从（0,0）开始不断向四周深度遍历，并使用visited数组保存已遍历过的元素
 * - 如果有遇到和字符串开头相同的字符，则使用标记i，标识当前已匹配的字符，往四周遍历，遇到再次匹配的字符，则标记增加，直到word遍历结束
 * -- 如果中途遇到，四周的字符都不匹配，则从从头开始遍历
 * 3、思考
 * - 是否需要每次从数组中某一个位置元素开始遍历？
 * - 还是从(0,0)位置开始出发？
 * - 如果word单次存在两个相同的字符，要是在数组中存在匹配字符的情况，则该字符应该有分支情况
 * -- 但是又因为已经visited遍历过了，不能再遍历，看样子还是要全部遍历，从遍历位置开始往四周深度遍历
 * - 每次从新遍历 visited 数组都重新重置为false
 * 4、总结
 * - 你牛逼，这不应该算中等题，应该算困难题目
 * - 二维数组，元素重置使用fill函数
 * - 遍历所有元素，以该元素作为起点开始遍历，
 * - 采用dfs深度优先遍历算法进行查找
 * - 边界判断
 * - 是否遍历过标记处理
 * - 从当前元素往四周扩散遍历递归判断
 */
fun exist(board: Array<CharArray>, word: String): Boolean {
    val m = board.size
    val n = board[0].size
    val visited = Array(m) {
        BooleanArray(n) {
            false
        }
    }

    visited.print()
    existBoolean = false
    for (i in 0 until m) {
        for (j in 0 until n) {
            for (x in 0 until m) {
                visited[x].fill(false)
            }
            println("for i:$i,j:$j")
            visited.print()
            if (existBoolean) {
                return true
            }
            if (board[i][j] == word[0]) {
                existReal(i, j, 0, visited, m, n, board, word)
                if (existBoolean) {
                    return true
                }
            }
        }
    }
    return false
}

var existBoolean = false

fun existReal(i: Int, j: Int, count: Int, visited: Array<BooleanArray>, m: Int, n: Int,
    board: Array<CharArray>, word: String) {
    println("existReal i:$i,j:$j,count:$count")
    if (existBoolean) {
        return
    }

    if (count == word.length) {
        existBoolean = true
        return
    }

    // 边界处理
    if (i < 0 || m <= i) {
        return
    }
    if (j < 0 || n <= j) {
        return
    }
    if (visited[i][j]) {    // 已经遍历过了
        return
    }

    // 当前字符是否相等
    if (board[i][j] != word[count]) {   // 不相等，直接返回false
        return
    }
    visited[i][j] = true

    /**
     * 往四周遍历
     * 边界问题处理：
     */

    // 上
    existReal(i - 1, j, count + 1, visited, m, n, board, word)

    // 下
    existReal(i + 1, j, count + 1, visited, m, n, board, word)

    // 左
    existReal(i, j - 1, count + 1, visited, m, n, board, word)

    // 右
    existReal(i, j + 1, count + 1, visited, m, n, board, word)

    visited[i][j] = false

}
