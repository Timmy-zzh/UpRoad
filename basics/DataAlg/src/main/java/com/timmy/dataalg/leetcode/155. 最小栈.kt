package com.timmy.dataalg.leetcode

import java.util.Stack
import kotlin.math.min

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。

实现 MinStack 类:
MinStack() 初始化堆栈对象。
void push(int val) 将元素val推入堆栈。
void pop() 删除堆栈顶部的元素。
int top() 获取堆栈顶部的元素。
int getMin() 获取堆栈中的最小元素。

示例 1:
输入：
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

输出：
[null,null,null,null,-3,null,0,-2]

解释：
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.getMin();   --> 返回 -2.

提示：
-231 <= val <= 231 - 1
pop、top 和 getMin 操作总是在 非空栈 上调用
push, pop, top, and getMin最多被调用 3 * 104 次
 */
fun main() {
}

/**
 * 1、审题：
 * - 实现一个栈，除了正常的栈功能，还要提供获取栈中最小元素值得方法
 * 2、解题：
 * - 使用系统Stack存储栈元素
 * - 使用变量minVal，维护一个最小值，在push，和pop的是否判断最小值，是否发生变化，如果变化，则需要更新minVal值
 */
class MinStack() {

    private val stack = Stack<Int>()
    var minVal = Int.MAX_VALUE

    fun push(`val`: Int) {
        stack.push(`val`)
        if (`val` < minVal) {
            minVal = `val`
        }
    }

    fun pop() { // 弹栈
        val pop = stack.pop()

        if (pop == minVal) { // 弹出了最小值，则需要更新
            val size = stack.size
            minVal = Int.MAX_VALUE
            for (i in 0 until size) {
                val item = stack[i]
                if (item < minVal) {
                    minVal = item
                }
            }
        }
    }

    fun top(): Int {
        return stack.peek()
    }

    fun getMin(): Int {
        if (stack.isEmpty()) {
            return -1
        }
        return minVal
    }
}