package com.gyy.guoLinKt.brocast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "收到自定义广播了", Toast.LENGTH_SHORT).show()
    }
}
