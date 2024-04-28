package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words， 返回所有二维网格上的单词 。
单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。

示例 1：
输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
输出：["eat","oath"]

示例 2：
输入：board = [["a","b"],["c","d"]], words = ["abcb"]
输出：[]

提示：
m == board.length
n == board[i].length
1 <= m, n <= 12
board[i][j] 是一个小写英文字母
1 <= words.length <= 3 * 104
1 <= words[i].length <= 10
words[i] 由小写英文字母组成
words 中的所有字符串互不相同
 */
fun main() {
//    val board = arrayOf(charArrayOf('a'))
//    val findWords = findWords(board, arrayOf("a"))
    val board = arrayOf(
        charArrayOf('a','b','c','e'),
        charArrayOf('x','x','c','d'),
        charArrayOf('x','x','b','a'),
    )
    val findWords = findWords(board, arrayOf("abc","abcd"))
    findWords.print()
}

/**
 * 1、审题：
 * - 输入一个二维字符串矩阵，和一个字符串集合，求字符串集合中的单词在二维矩阵中是否存在匹配的单词，如果存在添加到集合中返回
 * 2、解题：
 * - bfs 广度优先遍历
 * - 双层for循环
 * -- 先遍历字符串单词集合
 * -- 再遍历二维字符串矩阵，查找其中是否存在一个字符，与遍历的单词第一个字符相同，存在则进行bfs查找
 * 3、注意
 * - 标记是否已遍历过 visited
 * - 二维数组只有一个数据时，遍历过后，如何执行到下一步？
 * -- 变一下，先判断这个字母是否匹配，接着判断是否遍历到了最后一个字符串的尾部，是的话就说明该字符串匹配
 * - 回溯规则，dfs遍历完后一个流程后，结束要还原遍历踪迹，
 */
fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
    val resList = mutableSetOf<String>()
    val col = board.size
    val row = board[0].size
    val visited = Array(col) {
        Array(row) { false }
    }

    words.forEach { word ->
        for (i in 0 until col) {
            for (j in 0 until row) {

                for (x in 0 until col) {
                    for (y in 0 until row) {
                        visited[x][y] = false
                    }
                }
                println("for for -----i:$i, j:$j,board[i][j]:${board[i][j]} ")
                if (board[i][j] == word[0] && !visited[i][j]) { // 存在第一个相同的字符，进行dfs查找
                    visited[i][j] = true
                    dfs212(resList, visited, board, i, j, word, 0, word.length)
                }
            }
        }
    }
    return resList.toList()
}

fun dfs212(resList: MutableSet<String>, visited: Array<Array<Boolean>>, board: Array<CharArray>,
    i: Int, j: Int, word: String, n: Int, size: Int) {
    println("dfs212 i:$i, j:$j,board[i][j]:${board[i][j]} ,word:$word,word[n]:${word[n]} n:$n,size:$size")
//    visited.print()

    if (board[i][j] != word[n]) {   // 遍历过程中没遇到相同的单词了
        return
    }

    if (n == size-1) {
        resList.add(word)
        return
    }

    val col = board.size
    val row = board[0].size
    println("dfs212 col:$col, row:$row")

    // 往四个方向，继续深入dfs遍历
    if (i - 1 >= 0 && !visited[i - 1][j]) {
        visited[i - 1][j] = true
        dfs212(resList, visited, board, i - 1, j, word, n + 1, size)
        visited[i - 1][j] = false
    }
    if (i + 1 < col && !visited[i + 1][j]) {
        visited[i + 1][j] = true
        dfs212(resList, visited, board, i + 1, j, word, n + 1, size)
        visited[i + 1][j] = false
    }

    if (j - 1 >= 0 && !visited[i][j - 1]) {
        visited[i][j - 1] = true
        dfs212(resList, visited, board, i, j - 1, word, n + 1, size)
        visited[i][j - 1] = false
    }
    if (j + 1 < row && !visited[i][j + 1]) {
        visited[i][j + 1] = true
        dfs212(resList, visited, board, i, j + 1, word, n + 1, size)
        visited[i][j + 1] = false
    }
}

