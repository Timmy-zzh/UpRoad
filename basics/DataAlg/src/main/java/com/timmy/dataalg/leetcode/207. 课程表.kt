package com.timmy.dataalg.leetcode

import com.timmy.dataalg._10recursion.map
import com.timmy.dataalg.print

/**
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。

在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，
其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。

例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。

示例 1：
输入：numCourses = 2, prerequisites = [[1,0]]
输出：true
解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。

示例 2：
输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
输出：false
解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。

提示：
1 <= numCourses <= 2000
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
prerequisites[i] 中的所有课程对 互不相同
 */
fun main() { //    val prerequisites: Array<IntArray> = arrayOf(
    //        intArrayOf(1, 0),
    //        intArrayOf(0, 1),
    //    )
    //    val res = canFinish(2, prerequisites)
    val prerequisites: Array<IntArray> = arrayOf(
        intArrayOf(1, 0),
        intArrayOf(0, 2),
        intArrayOf(2, 1),
    )
    val res = canFinish(3, prerequisites)
    println("res:$res")
}

/**
 * 1、审题：
 * - 输入需要选修课程的一共门数，和先修课程的要求，为一个二维数组[a,b]，标识要修课程a，必须先修课程b
 * - 判断输入的这种课程安排是否可以完成
 * 2、解题：
 * - 首先要选修的课程一共是n，先修课程中二维数组的值a，b范围在[0,n-1]之间，
 * - 采用哈希散列表的方式进行求解，将课程a，需要的所有先修课程保存到set集合中，
 * - 继续遍历如果发现在先前的课程map中，有相反方向的要求先修课的，则说明存在死循环，返回false
 * 3、嵌套依赖
 * - [[1,0],[0,2],[2,1]]
 */
fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
    val map = mutableMapOf<Int, Set<Int>>()
    val size = prerequisites.size
    prerequisites.print()
    for (i in 0 until size) {
        val a = prerequisites[i][0]
        val b = prerequisites[i][1]
        println("a:$a , b:$b")

        if (a == b) {
            return false
        }

        // 先判断是否有循环依赖
        if (map.containsKey(b)) {
            val preSet = map[b] as MutableSet<Int>
            println("preSet:$preSet")
            if (preSet.contains(a)) {
                return false
            }
        }

        // 没有则添加到map集合中
        val orDefault = map.getOrDefault(a, mutableSetOf())
        val setOf: MutableSet<Int> = orDefault as MutableSet<Int>
        setOf.add(b)
        map[a] = setOf
        println("setOf:$setOf")
    }

    return true
}