package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print
import java.util.LinkedList

/**
 * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，
 * 其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。

例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。

示例 1：
输入：numCourses = 2, prerequisites = [[1,0]]
输出：[0,1]
解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。

示例 2：
输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
输出：[0,2,1,3]
解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。

示例 3：
输入：numCourses = 1, prerequisites = []
输出：[0]

提示：
1 <= numCourses <= 2000
0 <= prerequisites.length <= numCourses * (numCourses - 1)
prerequisites[i].length == 2
0 <= ai, bi < numCourses
ai != bi
所有[ai, bi] 互不相同
 */
fun main() {

    val arrar = arrayOf(
        intArrayOf(1, 0),
        intArrayOf(2, 0),
        intArrayOf(3, 1),
        intArrayOf(3, 2),
    )
    val res = findOrder(4, arrar)
    res.print()

}

/**
 * 1、审题：
 * - 输入要学习的课程数量 numCourses，和选修课程的前置课程要求，求所有课程学习的顺序，有多个结果，返回一个就行
 * 2、解题：
 * - 拓扑排序算法，和之前的题目一样
 * - 计算每个节点的入度
 * - 找到节点入度为0的结点，插入到队列queue中，接着while循环遍历其中的结点，将它指向其他节点的入度减1，
 * - 当遇到其他节点的入度为0时，就将该结点插入到队列中
 */
fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
    val resArr = mutableListOf<Int>()
    val queue = LinkedList<Int>()  // 保存的入度0的元素下标位置
    val inputs = Array(numCourses) { 0 } // 所有元素的入度

    // 遍历数组，根据先修课程的要求，将arr[0]位置的入度增加1
    for (num in prerequisites) {
        inputs[num[0]] = inputs[num[0]] + 1
    }
    inputs.print()
    for (i in inputs.indices) {
        if (inputs[i] == 0) {
            queue.add(i)
        }
    }

    while (queue.isNotEmpty()) {
        val pop = queue.pop()
        resArr.add(pop)
        for (arr in prerequisites) {
            if (arr[1] == pop) {
                inputs[arr[0]] = inputs[arr[0]] - 1
                if (inputs[arr[0]] == 0) {
                    queue.add(arr[0])
                }
            }
        }
    }

    for (i in inputs.indices) {     // 判断是否有相互依赖的结点还存在，入度还不为0
        if (inputs[i] != 0) {
            return intArrayOf()
        }
    }

    return resArr.toIntArray()
}