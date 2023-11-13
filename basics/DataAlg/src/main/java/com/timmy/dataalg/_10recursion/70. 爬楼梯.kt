package com.timmy.dataalg._10recursion

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

示例 1：
输入：n = 2
输出：2
解释：有两种方法可以爬到楼顶。
1. 1 阶 + 1 阶
2. 2 阶

示例 2：
输入：n = 3
输出：3
解释：有三种方法可以爬到楼顶。
1. 1 阶 + 1 阶 + 1 阶
2. 1 阶 + 2 阶
3. 2 阶 + 1 阶
 */
fun main() {
    val res = climbStairs(5)
    println("res:$res")

}

/**
 * 1、审题：爬楼梯
 * 2、解法：递归
 * - 1级楼梯只有一种爬法，2级楼梯有2种爬法，
 * - n级楼梯的解法是 f(n) = f(n-1) + f(n-2)
 * 3、总结：递归，
 * - 优化：采用散列表结构保存已经计算过得结果
 */
val map = mutableMapOf<Int, Int>()
fun climbStairs(n: Int): Int {
    if (n == 1)
        return 1
    if (n == 2)
        return 2
    if (map.containsKey(n)) {
        return map[n]!!
    }
    val num = climbStairs(n - 1) + climbStairs(n - 2)
    map[n] = num

    return num
}