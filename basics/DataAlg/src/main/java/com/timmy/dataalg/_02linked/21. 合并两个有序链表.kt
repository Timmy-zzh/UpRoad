package com.timmy.dataalg._02linked

import com.example.leetcode.bean.ListNode
import com.timmy.dataalg.print

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。

示例 1：
输入：l1 = [1,2,4], l2 = [1,3,4]
输出：[1,1,2,3,4,4]

示例 2：
输入：l1 = [], l2 = []
输出：[]

示例 3：
输入：l1 = [], l2 = [0]
输出：[0]
 */
fun main() {

    val head11 = ListNode(1)
    val head12 = ListNode(2)
    val head13 = ListNode(4)

    val head21 = ListNode(1)
    val head22 = ListNode(3)
    val head23 = ListNode(4)

    head11.next = head12
    head12.next = head13

    head21.next = head22
    head22.next = head23

    head11.print()
    head21.print()
    val mergeTwoLists = mergeTwoLists(head11, head21)
    mergeTwoLists.print()
}


/**
 * 1、审题：输入两个有序链表，将两个有序链表合并为一个有序链表
 * 2、解题：
 * - 遍历两个遍历结点
 * - 判断结点的大小，并将新节点插入到新链表中
 * - 新建虚拟头结点
 * 3、总结：
 * - 同时遍历两个链表结点，找出数值更小的结点，添加到新链表中，
 * - 数值小节点往后遍历
 */
fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    val emptyNode = ListNode(-1)
    var node: ListNode? = emptyNode
    var node1 = list1
    var node2 = list2
    while (node1 != null || node2 != null) {
        val num1 = node1?.`val` ?: Int.MAX_VALUE
        val num2 = node2?.`val` ?: Int.MAX_VALUE
//        println("num1=$num1,num2=$num2")
        if (num1 < num2) {
            node?.next = node1
            node1 = node1?.next
        } else {
            node?.next = node2
            node2 = node2?.next
        }

        node = node?.next
    }
    return emptyNode.next
}











