package com.timmy.dataalg.leetcode

import com.timmy.dataalg.bean.ListNode

/**
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表
。如果是，返回 true ；否则，返回 false 。

示例 1：
输入：head = [1,2,2,1]
输出：true

示例 2：
输入：head = [1,2]
输出：false

提示：
链表中节点数目在范围[1, 105] 内
0 <= Node.val <= 9
进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
fun main() {
}

/**
 * 1、审题：
 * - 输入一个链表结构，判断链表是否是回文链表
 * 2、解题：
 * - 方式1：将链表中所有元素值保存到集合中，然后判断该集合中的元素是否是回文
 */
fun isPalindrome(head: ListNode?): Boolean {
    val list = mutableListOf<Int>()
    var node = head
    while (node != null) {
        list.add(node.`val`)
        node = node.next
    }
    var left = 0
    var right = list.size - 1
    while (left <= right) {
        if (list[left] != list[right]) {
            return false
        }
        left++
        right--
    }
    return true
}