package com.gyy.guoLinKt.activity

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * Lifecycle.Event.ON_CREATE只会分发一次，Lifecycle.Event.ON_DESTROY不会被分发。
 *
 * 第一个Activity进入时，ProcessLifecycleOwner将分派Lifecycle.Event.ON_START, Lifecycle.Event.ON_RESUME。
 * 而Lifecycle.Event.ON_PAUSE, Lifecycle.Event.ON_STOP，将在最后一个Activity退出后延迟分发。
 * 如果由于配置更改而销毁并重新创建活动，则此延迟足以保证ProcessLifecycleOwner不会发送任何事件。
 *
 * 作用：监听应用程序进入前台或后台
 */
class ProcessLifecycleObserver : DefaultLifecycleObserver {
    override fun onCreate(owner: LifecycleOwner) {
        //只会调用一次
        Log.i(TAG, "onCreate: ")
    }

    override fun onResume(owner: LifecycleOwner) {
        Log.i(TAG, "onResume: ")
    }

    override fun onPause(owner: LifecycleOwner) {
        Log.i(TAG, "onPause: ")
    }

    override fun onStart(owner: LifecycleOwner) {
        //进入前台
        Log.i(TAG, "onStart: ")
    }

    override fun onStop(owner: LifecycleOwner) {
        // 进入后台
        Log.i(TAG, "onStop: ")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        // 不会调用
        Log.i(TAG, "onDestroy: ")
    }

    companion object {
        private const val TAG = "TAG"
    }
}