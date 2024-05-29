package com.timmy.dataalg.leetcode

/**
 * 给你一个嵌套的整数列表 nestedList 。每个元素要么是一个整数，要么是一个列表；该列表的元素也可能是整数或者是其他列表。
 * 请你实现一个迭代器将其扁平化，使之能够遍历这个列表中的所有整数。

实现扁平迭代器类 NestedIterator ：
NestedIterator(List<NestedInteger> nestedList) 用嵌套列表 nestedList 初始化迭代器。
int next() 返回嵌套列表的下一个整数。
boolean hasNext() 如果仍然存在待迭代的整数，返回 true ；否则，返回 false 。

你的代码将会用下述伪代码检测：
initialize iterator with nestedList
res = []
while iterator.hasNext()
append iterator.next() to the end of res
return res
如果 res 与预期的扁平化列表匹配，那么你的代码将会被判为正确。

示例 1：
输入：nestedList = [[1,1],2,[1,1]]
输出：[1,1,2,1,1]
解释：通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。

示例 2：
输入：nestedList = [1,[4,[6]]]
输出：[1,4,6]
解释：通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。

提示：
1 <= nestedList.length <= 500
嵌套列表中的整数值在范围 [-106, 106] 内
 */
fun main() {

}

/**
 * 1、审题：
 * - 题目说了，嵌套的整数列表 nestedList，每个元素要么是一个整数，要么是一个列表，
 * - 列表中的元素和当前元素一样的构成，要么是一个整数，要么是列表这个格式
 * - 现在要将这个列表的所有元素按照顺序找出来
 * 2、解题：
 * - 上面的整数列表，相当于一棵树结构，而且是多叉树
 * - 那如何遍历这棵树的所有元素呢？深度优先遍历-dfs
 */
class NestedInteger {
    private var nestedList = mutableListOf<NestedInteger>()
    private var mValue: Int = 0

    constructor()

    constructor(value: Int) {
        mValue = value
    }

    fun isInteger(): Boolean {
        return nestedList.isNotEmpty()
    }

    fun getInteger(): Int? {
        return if (nestedList.isEmpty()) mValue else null
    }

    fun setInteger(value: Int): Unit {
        mValue = value
    }

    fun add(ni: NestedInteger): Unit {
        nestedList.add(ni)
    }

    fun getList(): List<NestedInteger>? {
        return nestedList.ifEmpty { null }
    }
}

class NestedIterator(nestedList: List<NestedInteger>) {
    private val intList = mutableListOf<Int>()
    var iterator: MutableIterator<Int>

    init {

        dfs341(nestedList)
        iterator = intList.iterator()
    }

    /**
     * 不断dfs遍历，将树中所有结点添加到集合中
     */
    private fun dfs341(nestedList: List<NestedInteger>) {
        nestedList.forEach {
            if (it.isInteger()) {
                intList.add(it.getInteger()!!)
            } else {
                dfs341(it.getList()!!)
            }
        }
    }

    fun next(): Int {
        return iterator.next()
    }

    fun hasNext(): Boolean {
        return iterator.hasNext()
    }
}