package com.gyy.guoLinKt.kotlin

import android.widget.Toast
import com.gyy.guoLinKt.activity.App

fun Int.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(App.context, this, duration).show()
}