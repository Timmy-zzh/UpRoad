package com.timmy.dataalg.leetcode


/**
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。
例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
返回复制链表的头节点。

用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
val：一个表示 Node.val 的整数。
random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
你的代码 只 接受原链表的头节点 head 作为传入参数。

示例 1：
输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]

示例 2：
输入：head = [[1,1],[2,1]]
输出：[[1,1],[2,1]]

示例 3：
输入：head = [[3,null],[3,0],[3,null]]
输出：[[3,null],[3,0],[3,null]]

提示：
0 <= n <= 1000
-104 <= Node.val <= 104
Node.random 为 null 或指向链表中的节点。
 */
fun main() {
    val node0 = Node(7)
    val node1 = Node(13)
    val node2 = Node(11)
    val node3 = Node(10)
    val node4 = Node(1)

    node0.next = node1
    node1.next = node2
    node2.next = node3
    node3.next = node4
    node1.random = node0
    node2.random = node4
    node3.random = node2
    node4.random = node0

    val res = copyRandomList(node0)
    res.print()
}

/**
 * 1、审题：
 * - 输入一个链表，链表的结点有两个数据，一个指向下一个节点的的next指针，另一个是随机指针指向链表中某一个位置元素
 * - 现在需要对该链表进行深拷贝，除了下一个节点位置需要拷贝外，随机指针的元素也需要建立联系
 * 2、解题：
 * - 先for循环，复制新链表，保存好下一个节点
 * - 接着复制随机节点的位置，复制前需要先查找，两层for循环了
 */
fun copyRandomList(node: Node?): Node? {
    if (node == null) {
        return null
    }
    val resNode: Node? = Node(node.`val`)
    var temp = node.next
    var resTemp: Node? = resNode
    while (temp != null) {  // 连接next节点
        val newNode = Node(temp.`val`)
        resTemp!!.next = newNode

        resTemp = resTemp.next!!
        temp = temp.next
    }
    node.print()
    resNode.print()

    temp = node
    resTemp = resNode
    while (temp != null) {  // 连接random节点

        if (temp.random != null) { // 先找到，他的random是哪个
            val randomNode = temp.random
            var tempRandomNode = node
            var tempResRandomNode = resNode
            while (tempRandomNode != null && tempRandomNode != randomNode) {
                tempRandomNode = tempRandomNode.next
                tempResRandomNode = tempResRandomNode?.next
            }

            // 找到了
            resTemp?.random = tempResRandomNode
        }

        temp = temp.next
        resTemp = resTemp?.next
    }

    return resNode
}

class Node(var `val`: Int) {
    var next: Node? = null
    var random: Node? = null
}

fun Node?.print() {
    var node: Node? = this
    while (node != null) {
        print("val:${node.`val`} ->,")
        node = node.next
    }
    println()
    println("---- random ---")
    var node2: Node? = this
    while (node2 != null) {
        print("randowm:${node2.random?.`val`} ->,")
        node2 = node2.next
    }
    println()
}