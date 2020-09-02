package com.gyy.guoLinKt.kotlin

import android.widget.Toast
import com.gyy.guoLinKt.activity.App
import java.lang.StringBuilder

fun String.letterCount(): Int {
    var count = 0
    for (char in this) {
        if (char.isLetter()) {
            count++
        }
    }
    return count
}

fun String.repeat(count: Int): String {
    val stringbuilder = StringBuilder()
    repeat(count) {
        stringbuilder.append(this)
    }
    return stringbuilder.toString()
}

//operator fun String.times(count: Int): String {
//    val stringbuilder = StringBuilder()
//    repeat(count) {
//        stringbuilder.append(this)
//    }
//    return stringbuilder.toString()
//}

operator fun String.times(count: Int): String = repeat(count)

fun StringBuilder.build(block: StringBuilder.() -> Unit): StringBuilder {
    block()
    return this
}

fun String.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(App.context, this, duration).show()
}