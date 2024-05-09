package com.timmy.dataalg.leetcode

import com.timmy.dataalg.bean.TreeNode

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

示例 1：
输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出：3
解释：节点 5 和节点 1 的最近公共祖先是节点 3 。

示例 2：
输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出：5
解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。

示例 3：
输入：root = [1,2], p = 1, q = 2
输出：1

提示：
树中节点数目在范围 [2, 105] 内。
-109 <= Node.val <= 109
所有 Node.val 互不相同 。
p != q
p 和 q 均存在于给定的二叉树中。
 */
fun main() {
}

/**
 * 2、dfs解法
 * - 不断往下遍历，判断当前遍历的结点值，是否有和p/q其中一个相等的节点值
 * - 在遍历的过程中，查找最近公共祖先节点，
 * - 分两种情况：
 * -- 根节点匹配p/q其中一个节点，左右子树中有一个匹配
 * -- 根节点没有匹配其中任何一个，左右子树分别匹配
 * 3、总结
 * - dfs深度优先遍历，判断当前遍历的结点，是否有和p，q相匹配的结点值
 * - 先往下不断遍历，拿到左右子树的返回值，通过返回值直到左右子树是否有匹配结果
 * - 递归的方法，也需要返回当前结果，标记当前结点是否有匹配的情况
 */
var ans: TreeNode? = null
fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    dfs236(root, p, q)
    return ans
}

fun dfs236(root: TreeNode?, p: TreeNode?, q: TreeNode?): Boolean {
    if (root == null) {
        return false
    } // 左右子树是否匹配
    val leftC = dfs236(root.left, p, q)
    val rightC = dfs236(root.right, p, q)

    if ((leftC && rightC) || ((leftC || rightC) && (root.`val` == p!!.`val` || root.`val` == q!!.`val`))) {
        ans = root
    }

    return leftC || rightC || (root.`val` == p!!.`val` || root.`val` == q!!.`val`)
}

/**
 * 1、审题：
 * - 输入一个二叉树和其中的两个节点，求这两个节点的最近公共祖先，
 * 2、解题
 * - 二分查找法，将二叉树分为两个部分，（左子树+根节点）和（右子树）
 * - 判断两部分中是否有其中一个节点值，有的话，则返回当前根节点为所求值
 * 3、思路总结：
 * - 考虑三种情况，根节点匹配其中一个元素，则左右子树其中一个匹配即可
 * - 根节点不匹配，左右子树必须全部匹配中
 * 4、超过时间限制
 * - 注意裁剪操作
 */
fun lowestCommonAncestor2(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    println("lowestCommonAncestor2 root:${root?.`val`},p:${p?.`val`},q:${q?.`val`}")
    if (root == null) {
        return null
    }
    val rootC = (root.`val` == p!!.`val` || root.`val` == q!!.`val`)
    val leftC = if (root.left != null) isContainOne(root.left, p, q!!) else false
    val rightC = if (root.right != null) isContainOne(root.right, p, q!!) else false

    // 有一种情况，根节点匹配一个数字，左子树中一个节点匹配一个数字，那就只能是leftC为true，rightC为false
    println("lowestCommonAncestor2 rootC:$rootC, leftC:$leftC,rightC:$rightC")
    if (rootC) {
        if (leftC || rightC) {
            return root
        }
    } else {
        if (leftC && rightC) {
            return root
        }
    }

    if (leftC) {
        return lowestCommonAncestor2(root.left, p, q)
    }
    return lowestCommonAncestor2(root.right, p, q)
}

fun isContainOne(root: TreeNode?, p: TreeNode, q: TreeNode): Boolean {
    println("isContainOne root:${root?.`val`},p:${p?.`val`},q:${q?.`val`}")
    if (root == null) {
        return false
    }
    if (root.`val` == p.`val` || root.`val` == q.`val`) {
        return true
    }
    return if (root.left != null && root.right != null) {
        isContainOne(root.left, p, q) || isContainOne(root.right, p, q)
    } else if (root.left != null) {
        isContainOne(root.left, p, q)
    } else {
        isContainOne(root.right, p, q)
    }
}
