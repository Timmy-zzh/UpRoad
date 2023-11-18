package com.timmy.dataalg.bean

class ListNode(val `val`: Int) {
    var next: ListNode? = null
}

class TreeNode(val `val`: Int) {

    constructor(`val`: Int, left: TreeNode?, right: TreeNode?) : this(`val`) {
        this.left = left
        this.right = right
    }

    constructor(`val`: Int, leftVal: Int?, rightVal: Int?) : this(`val`) {
        this.left = if (leftVal != null) TreeNode(leftVal) else null
        this.right = if (rightVal != null) TreeNode(rightVal) else null
    }

    var left: TreeNode? = null
    var right: TreeNode? = null
}