package com.gyy.guoLinKt.kotlin

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackerbar(
    string: String,
    actionText: String,
    duration: Int = Snackbar.LENGTH_SHORT,
    block: (() -> Unit)
) {
    val snackerbar = Snackbar.make(this, string, duration)
    snackerbar.setAction(actionText) {
        block()
    }
    snackerbar.show()
}

fun View.showSnackerbarInt(
    string: String,
    actionResInt: String,
    duration: Int = Snackbar.LENGTH_SHORT,
    block: (() -> Unit)
) {
    val snackerbar = Snackbar.make(this, string, duration)
    snackerbar.setAction(actionResInt) {
        block()
    }
    snackerbar.show()
}