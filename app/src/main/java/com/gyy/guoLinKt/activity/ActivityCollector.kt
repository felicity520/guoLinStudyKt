package com.gyy.guoLinKt.activity

import android.app.Activity
import android.util.Log

object ActivityCollector {
    private val activities = ArrayList<Activity>()

    fun addActivity(activity: Activity) {
        Log.e("addActivity", "$activity")
        activities.add(activity)
    }

    fun removeActivity(activity: Activity) {
        Log.e("removeActivity", "$activity")
        activities.remove(activity)
    }

    fun finishAll() {
        for (activity in activities) {
            Log.e("finishAll()", "$activity")
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
    }


}