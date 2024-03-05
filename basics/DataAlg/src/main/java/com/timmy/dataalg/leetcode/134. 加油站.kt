package com.timmy.dataalg.leetcode

import kotlin.math.cos

/**
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。

示例 1:
输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
输出: 3
解释:
从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
因此，3 可为起始索引。

示例 2:
输入: gas = [2,3,4], cost = [3,4,3]
输出: -1
解释:
你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
因此，无论怎样，你都不可能绕环路行驶一周。
 */
fun main() {
            val gas = intArrayOf(1, 2, 3, 4, 5)
            val cost = intArrayOf(3, 4, 5, 1, 2)

//    val gas = intArrayOf(2, 3, 4)
//    val cost = intArrayOf(3, 4, 3)

    //    val gas = intArrayOf(5, 1, 2, 3, 4)
    //    val cost = intArrayOf(4, 4, 1, 5, 1)
    val res = canCompleteCircuit(gas, cost)
    println("res:$res")
}

/**
 * 好复杂啊，转不过弯来
 */
fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
    val n = gas.size
    var currGas: Int
    var i = 0
    while (i < n) {
        currGas = gas[i]
        var currI = i
        println("for i:$i,currGas:$currGas")
        if (currGas >= cost[i]) {
            currGas -= cost[currI] // 消耗汽油
            currI++ // 开到下一站
            if (currI == n) {
                currI = 0
            }
            println("for-- i:$i,currI:$currI,currGas:$currGas")

            while (i != currI) {    // 判断能否驶向下一站?
                currGas += gas[currI] // 再加油
                println("-- while currI:$currI,currGas:$currGas, cost[currI]:${cost[currI]}")
                if (currGas < cost[currI]) {
                    break
                }
                currGas -= cost[currI]  // 消耗
                currI++
                if (currI == n) {
                    currI = 0
                }
                println("== while currI:$currI,currGas:$currGas")
            }

            println("i == currI:$currI,i:$i")
            if (i == currI) {
                return i
            } else {
                if (currI > i) {
                    i = currI
                } else {
                    i++
                }
            }
        } else {
            i++
        }
    }
    return -1
}

/**
 * 1、审题：
 * - 输入两个数组gas和cost，在一个环形路段上，有n个加油站，每个加油站都有存储石油gas[i]可以进行加油;
 * - 加油站之间存在距离cost，每个加油站行驶需要消耗汽油，从i号加油站到i+1号加油站需要消耗汽油cost[i]
 * - 现在可以在n个加油站中选中一个加油站作为初始点，从初始点开始加油，并驶向下一个加油点，如果能行驶一个环形，则返回该起始点
 * 2、解题：
 * - 单层for循环，从第i个加油站开始出发，先将加油站的油gas[i]加满,
 * - 接着判断从加油站i开始到下一个加油站i+1，需要消耗cost[i]的汽油，判断当前的车上的汽油是否满足行驶条件，
 * -- i到i+1，因为存在环形情况，会从第n-1个加油站，行驶到第0个加油站的情形，需要特殊处理，
 * - 所以先加油，接着判断是否能到达下一个加油站，能的话，继续往后行驶，直到回到原始点
 * 3、超时：
 * - 当加油站数量很多时，当前时间复杂度为n的平方，
 * - 两层for循环其实用不着，外层for循环i，当内层for循环j已经知道在该位置无法继续行驶时，则外层for循环i可以接着从内层for循环的j位置开始继续行驶
 */
fun canCompleteCircuit1(gas: IntArray, cost: IntArray): Int {
    val n = gas.size
    val res = 0
    var currGas: Int
    for (i in 0 until n) {
        currGas = gas[i]
        var currI = i
        println("for i:$i,currGas:$currGas")
        if (currGas >= cost[i]) {
            currGas -= cost[currI] // 消耗汽油
            currI++ // 开到下一站
            if (currI == n) {
                currI = 0
            }

            while (i != currI) {    // 判断能否驶向下一站?
                currGas += gas[currI] // 再加油
                println("-- while currI:$currI,currGas:$currGas, cost[currI]:${cost[currI]}")
                if (currGas < cost[currI]) {
                    break
                }
                currGas -= cost[currI]  // 消耗
                currI++
                if (currI == n) {
                    currI = 0
                }
                println("== while currI:$currI,currGas:$currGas")
            }
            println(" i == currI:$currI,i:$i")
            if (i == currI) {
                return i
            }
        }
    }
    return -1
}