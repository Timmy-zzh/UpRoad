package com.timmy.dataalg.leetcode

import com.timmy.dataalg.print

/**
 * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 * 元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。

考虑 nums 的唯一元素的数量为 k ，你需要做以下事情确保你的题解可以被通过：
更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。nums 的其余元素与 nums 的大小不重要。
返回 k 。
判题标准:
系统会用下面的代码来测试你的题解:

int[] nums = [...]; // 输入数组
int[] expectedNums = [...]; // 长度正确的期望答案
int k = removeDuplicates(nums); // 调用
assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
assert nums[i] == expectedNums[i];
}
如果所有断言都通过，那么您的题解将被 通过。

示例 1：
输入：nums = [1,1,2]
输出：2, nums = [1,2,_]
解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。

示例 2：
输入：nums = [0,0,1,1,1,2,2,3,3,4]
输出：5, nums = [0,1,2,3,4]
解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
 */
fun main() {
    val res = removeDuplicates(intArrayOf(0,0,1,1,1,2,2,3,3,4))
//    val res = removeDuplicates(intArrayOf(1, 1, 2))
    println("res:$res")
}

/**
 * 1、审题：
 * - 输入一个数组，这个数组是升序排列的，但是存在重复的元素出现，现在需要将重复的元素过滤，
 * - 将所有的元素往前移动，保证每个元素只出现一次，并返回最终存在的元素个数，这个个数不包含重复元素个数
 * 2、解题：
 * - 双指针解法
 * -- 声明快慢两个指针，刚开始两个指针都指向数组头元素，
 * -- 慢指针不动，快指针往后移动，判断快指针当前遍历的元素与前一个指针是否相同，如果相同继续往后遍历
 * --- 如果不相同，慢指针移动一位，并赋值快指针元素数据，直到快指针遍历结束
 */
fun removeDuplicates(nums: IntArray): Int {
    var slow = 0
    for (fast in 1 until nums.size) {
        if (nums[fast] != nums[fast - 1]) {
            nums[++slow] = nums[fast]
        }
    }
    nums.print()
    return ++slow
}








