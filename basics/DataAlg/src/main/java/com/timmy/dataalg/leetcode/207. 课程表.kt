package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print
import java.util.LinkedList

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
fun main() { //        val prerequisites: Array<IntArray> = arrayOf(
    //            intArrayOf(1, 0),
    //            intArrayOf(0, 1),
    //        )
    //        val res = canFinish(2, prerequisites)
//    val prerequisites: Array<IntArray> = arrayOf(
//        intArrayOf(1, 0),
//        intArrayOf(0, 2),
//        intArrayOf(2, 1),
//    )
//    val res = canFinish(3, prerequisites)
        val prerequisites: Array<IntArray> = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(2, 3),
            intArrayOf(1, 2),
            intArrayOf(3, 0),
        )
        val res = canFinish(4, prerequisites)
    println("res:$res")
}

/**
 * 2、解法：拓扑排序
 * - 入度，出度概念，拓扑排序，找出拓扑关系，使用二维数组保存，每个节点的入度，（入度表示该结点依赖的元素关系）
 * - while循环，不断寻找入度为0的结点，出队列，并且将该结点指向的下一个节点直接的关系中断，则这条边终点的入度数减少1
 * - 最后如果所有的结点入度都为0，则所有不存在环，或者相互依赖关系。
 */
fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
    val inputs = Array(numCourses) { 0 } // 节点入度表示， p[0] 依赖 p[1] ，说明有向边p[1]指向的是p[0]
    for (ints in prerequisites) {
        val ai = ints[0]
        val bi = ints[1]
        inputs[ai] = inputs[ai] + 1 // 求ai的入度
    }
    inputs.print()

    // 将入度为0的结点，保存到队列中
    val queue = LinkedList<Int>()
    for (i in inputs.indices) {  // indices 遍历他的下标标识
        if (inputs[i] == 0) {
            queue.add(i)
        }
    }

    println("queue:")
    queue.print() // 找到入度为0的结点，并将他的前驱依赖节点的入度减少1
    while (queue.isNotEmpty()) {
        val pop = queue.pop()
        println("pop:$pop")
        for (ints in prerequisites) {
            if (ints[1] == pop) {    // 找到入度为0的结点，再找到他的后驱目标节点，将后驱节点的入度减少1
                val ai = ints[0]
                inputs[ai] = inputs[ai] - 1

                if (inputs[ai] == 0) {
                    queue.add(ai)
                }
            }
        }
    }

    // 判断是否存在入度不为0的结点，存在则说明存在相互依赖关系
    inputs.print()
    for (input in inputs) {
        if (input != 0) {
            return false
        }
    }

    return true
}

fun canFinish22(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
    val setMap = mutableMapOf<Int, Set<Int>>()
    val keyMap = mutableMapOf<Int, Int>()
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
        if (setMap.containsKey(b)) {
            val preSet = setMap[b] as MutableSet<Int>
            println("preSet:$preSet")
            if (preSet.contains(a)) {
                return false
            }
        }

        // 没有则添加到map集合中
        val setA = setMap.getOrDefault(a, mutableSetOf()) as MutableSet<Int>
        setA.add(b)
        setMap[a] = setA
        println("setOf:$setA")

        /**
         * 嵌套依赖处理，将b包含的依赖项目也添加进来
         * 遍历map集合，查找所有的set中是否有包含b的set集合，有的话，那就需要新增
         */
        var tempV = b
        while (keyMap.containsValue(tempV)) {
            println("while keyMap:$keyMap")
            val tempK = keyMap[tempV]!!
            val mutableSet = setMap.getOrDefault(tempK, mutableSetOf()) as MutableSet<Int>
            mutableSet.addAll(setA)
            setMap[tempK] = mutableSet

            tempV = keyMap[tempK]!!
        }

        println("map:$setMap") //        keyMap[a] = b
        keyMap[b] = a
    }

    return true
}

/**
 * 1、审题：
 * - 输入需要选修课程的一共门数，和先修课程的要求，为一个二维数组[a,b]，标识要修课程a，必须先修课程 b
 * - 判断输入的这种课程安排是否可以完成
 * 2、解题：
 * - 首先要选修的课程一共是n，先修课程中二维数组的值a，b范围在[0,n-1]之间，
 * - 采用哈希散列表的方式进行求解，将课程a，需要的所有先修课程保存到set集合中，
 * - 继续遍历如果发现在先前的课程map中，有相反方向的要求先修课的，则说明存在死循环，返回false
 * 3、嵌套依赖
 * - [[1,0],[0,2],[2,1]]  一次循环解决
 * - [[0,1],[2,3],[1,2],[3,0]] --》这个要while循环才行
 */
fun canFinish0419(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
    val setMap = mutableMapOf<Int, Set<Int>>()
    val keyMap = mutableMapOf<Int, Int>()
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
        if (setMap.containsKey(b)) {
            val preSet = setMap[b] as MutableSet<Int>
            println("preSet:$preSet")
            if (preSet.contains(a)) {
                return false
            }
        }

        // 没有则添加到map集合中
        val setA = setMap.getOrDefault(a, mutableSetOf()) as MutableSet<Int>
        setA.add(b)
        setMap[a] = setA
        println("setOf:$setA")
    }

    return true
}