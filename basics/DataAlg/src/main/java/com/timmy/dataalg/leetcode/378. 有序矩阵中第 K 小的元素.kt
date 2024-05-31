package com.timmy.dataalg.leetcode

/**
 * 给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
你必须找到一个内存复杂度优于 O(n2) 的解决方案。

示例 1：
输入：matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
输出：13
解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13

示例 2：
输入：matrix = [[-5]], k = 1
输出：-5

提示：
n == matrix.length
n == matrix[i].length
1 <= n <= 300
-109 <= matrix[i][j] <= 109
题目数据 保证 matrix 中的所有行和列都按 非递减顺序 排列
1 <= k <= n2

进阶：
你能否用一个恒定的内存(即 O(1) 内存复杂度)来解决这个问题?
你能在 O(n) 的时间复杂度下解决这个问题吗?这个方法对于面试来说可能太超前了，但是你会发现阅读这篇文章（ this paper ）很有趣。
 */
fun main() {

}

/**
 * 1、审题：
 * - 输入一个二维矩阵，和数字k，二维矩阵的行和列都是升序排列的，要求二维矩阵中第k小的数字
 * 2、解题：
 * - 将二维矩阵中所有元素，获取出来放到集合中按升序排序，然后找到第k位的元素返回集合
 * -可进行拆分：既然所有行都是升序的，那可以将所有的行进行两两合并，
 * 3、总结：
 * - 应该采用二分查找法
 */
fun kthSmallest(matrix: Array<IntArray>, k: Int): Int {
    val resList = mutableListOf<Int>()
    resList.addAll(matrix[0].asList())
    val rows = matrix.size
    for (row in 1 until rows) {
        mergeArray(resList, matrix[row])
    }
    return resList[k - 1]
}

/**
 * 将resList中的集合元素，和ints中的数组合并，并将合并后的结果保存到resList中
 */
fun mergeArray(resList: MutableList<Int>, ints: IntArray) {
    val tempList = mutableListOf<Int>()
    var left = 0
    var right = 0
    while (left < resList.size || right < ints.size) { // 取出最小值
        if (left == resList.size) {
            tempList.add(ints[right])
            right++
            continue
        }
        if (right == ints.size) {
            tempList.add(resList[left])
            left++
            continue
        }
        if (resList[left] <= ints[right]) {
            tempList.add(resList[left])
            left++
        } else {
            tempList.add(ints[right])
            right++
        }
    }
    resList.clear()
    resList.addAll(tempList)
}
