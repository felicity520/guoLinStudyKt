package com.gyy.guoLinKt.activity

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ProcessLifecycleOwner

/**
 * @author Flywith24
 * @date   2020/7/1
 * time   9:26
 * description
 */
class App : Application() {

    private val processLifecycleObserver by lazy { ProcessLifecycleObserver() }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        ProcessLifecycleOwner.get().lifecycle.addObserver(processLifecycleObserver)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        val isForeground =
            ProcessLifecycleOwner.get().lifecycle.currentState.isAtLeast(
                Lifecycle.State.STARTED
            )
    }

}