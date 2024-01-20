package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。

示例 1：
输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]

示例 2：
输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 */
fun main() { //    val matirx = arrayOf(
    //        intArrayOf(1, 2, 3),
    //        intArrayOf(4, 5, 6),
    //        intArrayOf(7, 8, 9),
    //    )
    //    val matirx = arrayOf(
    //        intArrayOf(1, 2, 3, 4),
    //        intArrayOf(5, 6, 7, 8),
    //        intArrayOf(9, 10, 11, 12),
    //        intArrayOf(13, 14, 15, 16),
    //    )
    val matirx = arrayOf(
        intArrayOf(1),
    )
    matirx.print()
    val res = spiralOrder(matirx)
    println("res:")
    res.print()
}

/**
 * 1、审题
 * - 输入一个m*n的数组，对数组元素进行绕圈遍历，顺时针从最外圈遍历一圈，后接着再往里一层遍历一圈，知道所有元素遍历完成
 * 2、解题
 * - 用一个boolean数组标记遍历过的元素？
 * - 每绕一圈，用一个标记记下来，
 * - 每圈的开始位置都是[i,i] 对角线位置。
 * - 绕圈的规律，先从左到右（j->n），再从上到下(i->m)，从右到左（）。
 * - 一共要处理五个节点：每圈的四个角，和最后一个位置，需要往右转移
 * - 最后的结束位置怎么弄？用一个boolean数组标记
 * 3、总结
 * - 绕圈
 * - 处理的四条边
 * - 一圈完毕处理
 * - 绕圈的数量
 */
fun spiralOrder(matrix: Array<IntArray>): List<Int> {
    val resList = ArrayList<Int>()
    var i = 0
    var j = 0
    val m = matrix.size
    val n = matrix[0].size
    println("m:$m, n:$n")
    val visited = Array(m) {
        BooleanArray(n) { false }
    }
    var circle = -1 // 标记当前是第几圈
    var temp = -1 // 标记当前是第几圈

    while (i < m && j < n) {
        println("i:$i, j:$j ,circle:$circle")
        visited.print()
        if (i == j && visited[i][j]) {
            i++
            j++
        }
        if (i >= m || j >= n || visited[i][j]) {
            break
        }
        resList.add(matrix[i][j])
        visited[i][j] = true

        //        marked.print()

        /**
         *  下一步往那边走
         */

        // 对角线
        if (i == j) {
            temp++
            circle = temp / 2
        }

        if (i == circle && j != n - 1 - circle) { // 1、起始行,没到最后一列
            j++
            continue
        }

        if (j == n - 1 - circle && i != m - 1 - circle) { // 2、右边列
            i++
            continue
        }

        if (i == m - 1 - circle && j != circle) { // 3、底部行
            j--
            continue
        }

        if (j == circle && i != circle) { // 4、起始列
            i--
        }
    }

    return resList
}