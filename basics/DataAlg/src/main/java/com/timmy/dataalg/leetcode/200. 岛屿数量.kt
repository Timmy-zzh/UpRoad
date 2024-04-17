package com.timmy.dataalg.leetcode

import java.util.Deque
import java.util.LinkedList
import java.util.Queue

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
此外，你可以假设该网格的四条边均被水包围。

示例 1：
输入：grid = [
["1","1","1","1","0"],
["1","1","0","1","0"],
["1","1","0","0","0"],
["0","0","0","0","0"]
]
输出：1

示例 2：
输入：grid = [
["1","1","0","0","0"],
["1","1","0","0","0"],
["0","0","1","0","0"],
["0","0","0","1","1"]
]
输出：3

提示：
m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] 的值为 '0' 或 '1'
 */
fun main() {
    val grid = arrayOf(
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('0', '0', '1', '0', '0'),
        charArrayOf('0', '0', '0', '1', '1'),
    )
    val res = numIslands(grid)
    println("res:$res")
}

/**
 * 1、审题：
 * - 输入一个二维数组，数组元素由0和1标识水和陆地，相连的陆地组成一座岛屿，求这片区域的岛屿数量
 * 2、解题： dfs - 深度优先算法 - 使用队列保存数据
 * - 使用bool二位数，标识元素是否已经被访问过了
 * - 队列queue保存遍历的元素
 * - 双层for循环，查找每个元素，遇到陆地则保存到队列中，并从队列中取出元素，并以当前元素为基点向四周扩展
 * -- 遇到元素值是1，且没有访问过的，则添加到队列中，直到队列为空，则继续双层for循环的遍历
 * - 岛屿的数量就是双层for循环遇到陆地的数量
 * 3、总结
 * - bfs 广度优先算法， 标识已经访问过，在添加到队列的时候就翻转值，可避免重复添加
 */
fun numIslands(grid: Array<CharArray>): Int {
    val row = grid.size
    val col = grid[0].size
    val visited = Array(row) {
        BooleanArray(col) { false }
    }

    var res = 0
    val queue = LinkedList<IntArray>()
    for (i in 0 until row) {
        for (j in 0 until col) {
            if (visited[i][j]) {
                continue
            }
            if (grid[i][j] == '1') {
                res++
                queue.add(intArrayOf(i, j))
                visited[i][j] = true

                while (queue.isNotEmpty()) {
                    val itemArr = queue.pop()
                    val ii = itemArr[0]
                    val jj = itemArr[1]
                    println("ii:$ii,jj:$jj")
//                    visited[ii][jj] = true

                    // 往四周扩散
                    if (ii - 1 >= 0 && grid[ii - 1][jj] == '1' && !visited[ii - 1][jj]) {
                        queue.add(intArrayOf(ii - 1, jj))
                        visited[ii - 1][jj] = true
                    }
                    if (ii + 1 < row && grid[ii + 1][jj] == '1' && !visited[ii + 1][jj]) {
                        queue.add(intArrayOf(ii + 1, jj))
                        visited[ii + 1][jj] = true
                    }
                    if (jj - 1 >= 0 && grid[ii][jj - 1] == '1' && !visited[ii][jj - 1]) {
                        queue.add(intArrayOf(ii, jj - 1))
                        visited[ii][jj - 1] = true
                    }
                    if (jj + 1 < col && grid[ii][jj + 1] == '1' && !visited[ii][jj + 1]) {
                        queue.add(intArrayOf(ii, jj + 1))
                        visited[ii][jj + 1] = true
                    }
                }
            }
        }
    }
    return res
}