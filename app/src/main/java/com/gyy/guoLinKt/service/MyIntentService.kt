package com.gyy.guoLinKt.service

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MyIntentService() : IntentService("MyIntentService") {

    companion object {
        private const val TAG = "MyIntentService"
    }

    override fun onHandleIntent(p0: Intent?) {
        Log.e(TAG, "onHandleIntent Thread.currentThread().name = ${Thread.currentThread().name}")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy--------------")
    }
}