package com.timmy.dataalg.leetcode

import kotlin.random.Random

/**
 * 实现RandomizedSet 类：
RandomizedSet() 初始化 RandomizedSet 对象
bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。

示例：
输入
["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
[[], [1], [2], [2], [], [1], [2], []]
输出
[null, true, false, true, 2, true, false, 2]
解释
RandomizedSet randomizedSet = new RandomizedSet();
randomizedSet.insert(1); // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
randomizedSet.remove(2); // 返回 false ，表示集合中不存在 2 。
randomizedSet.insert(2); // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
randomizedSet.getRandom(); // getRandom 应随机返回 1 或 2 。
randomizedSet.remove(1); // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
randomizedSet.insert(2); // 2 已在集合中，所以返回 false 。
randomizedSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。

提示：
-231 <= val <= 231 - 1
最多调用 insert、remove 和 getRandom 函数 2 * 105 次
在调用 getRandom 方法时，数据结构中 至少存在一个 元素。
 */
fun main() {

}

/**
 * 1、审题：
 * - 设计一个数据结构，可以插入数据，删除数据，和随机获取集合中的一个元素
 * 2、解题：
 * - 底层使用集合保存数据
 * -- 插入数据时，先判断是否存在该元素，存在则不插入，并返回false
 * -- 删除数据，元素存在时才删除成功
 * -- 获取元素时，一定要有元素才行，获取的元素个数，使用随机数然后对集合大小取余，保存获取集合的位置在集合中
 */
class RandomizedSet() {

    val list = mutableListOf<Int>()

    fun insert(`val`: Int): Boolean {
        if (list.contains(`val`)) {
            return false
        }
        list.add(`val`)
        return true
    }

    fun remove(`val`: Int): Boolean {
        if (list.contains(`val`)) {
            list.remove(`val`)
            return true
        }
        return false
    }

    fun getRandom(): Int {
        var randomIndex = (0 until  list.size).random()
        return list[randomIndex]
    }

}