package com.gyy.guoLinKt.bean

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class SimpleWorker(context: Context, parameters: WorkerParameters) : Worker(context, parameters) {

    override fun doWork(): Result {
        Log.d("SimpleWorker", "SimpleWorker工作中")
        return Result.success()
    }
}