package com.timmy.dataalg.leetcode

import com.timmy.dataalg.bean.ListNode

/**
 * 给你一个链表的头节点 head ，判断链表中是否有环。
如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，
评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
如果链表中存在环 ，则返回 true 。 否则，返回 false 。

示例 1：
输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。

示例 2：
输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点。

示例 3：
输入：head = [1], pos = -1
输出：false
解释：链表中没有环。

提示：
链表中节点的数目范围是 [0, 104]
-105 <= Node.val <= 105
pos 为 -1 或者链表中的一个 有效索引 。

进阶：你能用 O(1)（即，常量）内存解决此问题吗？
 */
fun main() {

}

/**
 * 1、审题：
 * - 输入一个链表，判断链表中是否存在环
 * 2、解题：
 * - 链表为单链表，节点有数据域和next指针域指向下一个节点，
 * - 如果存在节点，说明尾部节点的next指针指向前面的一个节点
 * - 通过双指针解法： fast快指针每次走两步，slow指针每次走一步，
 * -- 如果fast指针指向空，说明不存在环，如果fast和slow指针相遇，则说明链表存在环
 */
fun hasCycle(head: ListNode?): Boolean {
    if (head == null) {
        return false
    }
    var fast = head
    var slow = head

    while (fast != null && fast.next != null) {
        slow = slow!!.next
        fast = fast.next!!.next

        if (fast == slow) {
            return true
        }
    }
    return false
}