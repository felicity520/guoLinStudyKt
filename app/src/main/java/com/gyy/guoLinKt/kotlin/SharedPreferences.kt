package com.gyy.guoLinKt.kotlin

import android.annotation.SuppressLint
import android.content.SharedPreferences

@SuppressLint("CommitPrefEdits")
fun SharedPreferences.open(block: SharedPreferences.Editor.() -> Unit) {
    //当前是在SharedPreferences的顶层方法中，所以可以调用SharedPreferences的方法，比如edit()
    val edit = edit()
    //当前的open内部的上下文是Editor
    edit.block()
    edit.apply()
}
