package com.gyy.guoLinKt.kotlin

import android.os.Build
import androidx.annotation.RequiresApi
import java.lang.Integer.max


@RequiresApi(Build.VERSION_CODES.N)
fun main() {
    val a = 1
    val b = 2
    val c = 3
    val largest = max2(a, b, c)
    println(largest)
}

@RequiresApi(Build.VERSION_CODES.N)
fun max2(vararg nums: Int): Int {
    var numMax = Int.MIN_VALUE
    for (num in nums) {
        numMax = max(num, numMax)
    }
    return numMax
}

fun <T : Comparable<T>> max1(vararg nums: T): T {
    if (nums.isEmpty()) {
        throw RuntimeException("Params can not be empty")
    }
    var maxNum = nums[0]
    for (num in nums) {
        if (num > maxNum) {
            maxNum = num
        }
    }
    return maxNum
}