package com.timmy.dataalg.bean

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Node {
    var `val`: Int = 0
    var left: Node? = null
    var right: Node? = null
    var next: Node? = null
}

class TreeNode() {

    constructor(`val`: Int) : this() {
        this.`val` = `val`
    }

    constructor(`val`: Int, left: TreeNode?, right: TreeNode?) : this(`val`) {
        this.left = left
        this.right = right
    }

    constructor(`val`: Int, leftVal: Int?, right: TreeNode?) : this(`val`) {
        this.left = if (leftVal != null) TreeNode(leftVal) else null
        this.right = right
    }

    constructor(`val`: Int, left: TreeNode?, rightVal: Int?) : this(`val`) {
        this.left = left
        this.right = if (rightVal != null) TreeNode(rightVal) else null
    }

    constructor(`val`: Int, leftVal: Int?, rightVal: Int?) : this(`val`) {
        this.left = if (leftVal != null) TreeNode(leftVal) else null
        this.right = if (rightVal != null) TreeNode(rightVal) else null
    }

    var `val`: Int = 0
    var left: TreeNode? = null
    var right: TreeNode? = null
}