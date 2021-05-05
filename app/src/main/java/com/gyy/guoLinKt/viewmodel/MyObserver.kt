package com.gyy.guoLinKt.viewmodel

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import okhttp3.internal.notifyAll

class MyObserver(val lifecycle: Lifecycle) : LifecycleObserver {

    companion object {
        const val TAG = "MyObserver"
    }

    /**
     * 观察者的方法可以接受一个参数LifecycleOwner，就可以用来获取当前状态、或者继续添加观察者。
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun activityCreate(owner: LifecycleOwner) {
        Log.e(TAG, "activityCreate: -----currentState---------${owner.lifecycle.currentState}")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activityStart() {
        Log.e(TAG, "activityStart: ----currentState---------- ${lifecycle.currentState}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun activityResume() {
        Log.e(TAG, "activityResume: -----currentState---------${lifecycle.currentState}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun activityPause() {
        Log.e(TAG, "activityPause: -----currentState---------${lifecycle.currentState}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun activityStop() {
        Log.e(TAG, "activityStop: -----currentState---------${lifecycle.currentState}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun activityDestroy() {
        Log.e(TAG, "activityDestroy: -----currentState---------${lifecycle.currentState}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun activityOnAny(owner: LifecycleOwner, event: Lifecycle.Event) {
        Log.e(TAG, "event.name: -----currentState---------${event.name}")
    }

}