package com.timmy.dataalg._07graph

import com.timmy.dataalg.print
import java.util.LinkedList

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
 * 1、审题：输入一个数组，数组中的元素由0和1字符组成，连成一片的1为陆地，0标识水域，上下左右相连的1才能连成陆地
 * - 对角线相连不能称为陆地，查找数组中陆地的个数并返回
 * 2、解题：
 * - 遍历数组
 * - 使用一个visited数组标识元素是否已经遍历过了，
 * - 如果遇到字符1，则从该字符位置开始遍历上下左右四个方向的元素，把他放到队列中来（bfs），不断从队列中获取元素，直到队列为空
 */
fun numIslands(grid: Array<CharArray>): Int {
    val width = grid.size
    val height = grid[0].size // 二维数组
    val visited = Array(width) { BooleanArray(height) }
    visited.print()
    val queue = LinkedList<IntArray>()
    var nums = 0

    for (i in 0 until width) {
        for (j in 0 until height) {

            if (!visited[i][j] && grid[i][j] == '1') {
                println("----i=$i,j=$j")
                nums++
                queue.add(intArrayOf(i, j))
                visited[i][j] = true

                while (queue.isNotEmpty()) { // 遍历到的顶点
                    val vertial = queue.pop()
                    val w = vertial[0]
                    val h = vertial[1]
                    println("w=$w,h=$h")

                    // 遍历这个顶点的四周元素，是否等于字符1，等于的话添加到队列中 // left
                    if (w - 1 >= 0 && !visited[w - 1][h] && grid[w - 1][h] == '1') {
                        queue.add(intArrayOf(w - 1, h))
                        visited[w - 1][h] = true
                    }

                    // right
                    if (w + 1 < width && !visited[w + 1][h] && grid[w + 1][h] == '1') {
                        queue.add(intArrayOf(w + 1, h))
                        visited[w + 1][h] = true
                    }

                    // top
                    if (h - 1 >= 0 && !visited[w][h - 1] && grid[w][h - 1] == '1') {
                        queue.add(intArrayOf(w, h - 1))
                        visited[w][h - 1] = true
                    }

                    // bottom
                    if (h + 1 < height && !visited[w][h + 1] && grid[w][h + 1] == '1') {
                        queue.add(intArrayOf(w, h + 1))
                        visited[w][h + 1] = true
                    }
                }
            }
        }
    }
    return nums
}
















