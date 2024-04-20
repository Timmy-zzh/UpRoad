package com.timmy.dataalg.leetcode

import com.timmy.dataalg.bean.ListNode

/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。

示例 1：
输入：head = [1,2,3,4,5]
输出：[5,4,3,2,1]

示例 2：
输入：head = [1,2]
输出：[2,1]

示例 3：
输入：head = []
输出：[]

提示：
链表中节点的数目范围是 [0, 5000]
-5000 <= Node.val <= 5000
进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 */
fun main() {

}

/**
 * 1、审题：
 * - 输入一个单链表，对其进行翻转
 * 2、解题：
 * - 迭代，不断遍历当前链表，在遍历的过程中进行翻转
 * - 需要使用前置节点 prev，temp用于接收前置节点断开后的，后续链表
 * - 还需要使用空节点emptyNode，用于保存翻转后的链表
 */
fun reverseList(head: ListNode?): ListNode? {
    val emptyNode = ListNode(-1)
    var prev: ListNode
    var tempNode = head
    while (tempNode != null) {
        prev = tempNode     // 获取遍历到的节点
        tempNode = tempNode.next

        prev.next = emptyNode.next  // 将遍历到的节点插入到新链表的前面
        emptyNode.next = prev
    }
    return emptyNode.next
}