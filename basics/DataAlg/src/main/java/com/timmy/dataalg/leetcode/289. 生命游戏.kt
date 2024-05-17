package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 根据 百度百科 ， 生命游戏 ，简称为 生命 ，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
 *
给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：
1 即为 活细胞 （live），或 0 即为 死细胞 （dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：

如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，
其中细胞的出生和死亡是同时发生的。给你 m x n 网格面板 board 的当前状态，返回下一个状态。

示例 1：
输入：board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
输出：[[0,0,0],[1,0,1],[0,1,1],[0,1,0]]

示例 2：
输入：board = [[1,1],[1,0]]
输出：[[1,1],[1,1]]

提示：
m == board.length
n == board[i].length
1 <= m, n <= 25
board[i][j] 为 0 或 1

进阶：
你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
 */
fun main() {

    val board = arrayOf(
        intArrayOf(0, 1, 0),
        intArrayOf(0, 0, 1),
        intArrayOf(1, 1, 1),
        intArrayOf(0, 0, 0),
    )
    gameOfLife(board)
    board.print()
}

/**
 * 1、审题：
 * - 输入一个二维数组，数组元素由0（死细胞）和1（活细胞）组成，现在根据规则需要进行变化，
 * - 四条遵守规则如下：
 * -- 活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡
 * -- 活细胞周围八个位置有两个或三个活细胞，则该位置活细胞任然存活
 * -- 活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡
 * -- 死细胞周围正好有三个活细胞，则该位置死细胞复活
 * 2、解题：
 * - 根据原始二维数组，求得每个位置的元素周围八个位置的活细胞个数，根据当前位置的细胞状态和周围八个位置的活细胞个数
 * -- 对当前位置的细胞位置状态进行改变，两种状态变化 活细胞-》死细胞 （live nums<2,或者>3）
 * -- 死细胞 -》 活细胞 （live num = 3）
 * - 如何求出二维数组中某元素位置，周围八个位置的活细胞个数呢？
 * -- 也就是遍历周围3*3的区域，使用两层for循环，与当前元素位置进行判断，是否越界？和是否是活细胞，
 * -- 去除区域中心位置， int[] neighbor = [-1,0,1]
 * - 原始数组需要做变化，还需要一个相同内容的数组，用作数据内容不变，用于判断元素周围八个位置的活细胞个数
 * 3、进阶：
 * - 空间复杂度当前是O(mn),使用额外的一个状态保存，例如使用2表示从活细胞变为死细胞的情况，就可以用在原始二维数组基础上对整个数组的变化进行处理
 * 4、总结：
 * - 求周围八个位置的活细胞个数，使用3*3区域，对二维数组中的每个元素位置进行判断处理
 * - 规则的实现，通过额外的数组保存原始数据值，copy方法使用的浅拷贝
 */
fun gameOfLife(board: Array<IntArray>): Unit {
    var rows = board.size
    var cols = board[0].size

    var neighbor = intArrayOf(-1, 0, 1)

    val copyBoard = Array(rows) {  // 这种方式是浅拷贝
        IntArray(cols)
    }
    for (row in 0 until rows) {
        for (col in 0 until cols) {
            copyBoard[row][col] = board[row][col]
        }
    }
    copyBoard.print()

    // 遍历二维数组中的每个元素，计算当前遍历元素周围的活细胞个数，根据活细胞个数和规则，对原始数组进行状态值的切换
    for (row in 0 until rows) {
        for (col in 0 until cols) {

            println("for--- row:$row,col:$col")
            var liveNums = 0
            for (i in 0 until 3) {
                for (j in 0 until 3) {
                    if (!(neighbor[i] == 0 && neighbor[j] == 0)) {   // 去除3*3区域中心位置
                        // 四周位置的位置
                        val nRow = row + neighbor[i]
                        val nCol = col + neighbor[j]
                        println("forfor nRow:$nRow,nCol:$nCol") // 边界与活细胞个数判断
                        if ((nRow >= 0 && nRow < rows) && (nCol >= 0 && nCol < cols) && copyBoard[nRow][nCol] == 1) {
                            liveNums++
                            println("nRow:$nRow,nCol:$nCol liveNums++ ")
                        }
                    }
                }
            }
            println("last row:$row,col:$col,liveNums:$liveNums")

            // 规则处理
            if (board[row][col] == 1 && (liveNums < 2 || liveNums > 3)) {
                board[row][col] = 0
            }

            if (board[row][col] == 0 && liveNums == 3) {
                board[row][col] = 1
            }
        }
    }

    copyBoard.print()
}



















