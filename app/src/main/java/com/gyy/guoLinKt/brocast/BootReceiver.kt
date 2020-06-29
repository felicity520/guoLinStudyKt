package com.gyy.guoLinKt.brocast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "GYY 接收到开机广播了", Toast.LENGTH_SHORT).show()
    }
}
