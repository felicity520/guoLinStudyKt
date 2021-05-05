package com.gyy.guoLinKt.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.gyy.guoLinKt.R

/**
 * 有一个自定义类并希望使其成为LifecycleOwner，
 * 可以使用LifecycleRegistry类，
 * 它是Lifecycle的实现类，但需要将事件转发到该类
 */
class LifecycleActivity : AppCompatActivity(), LifecycleOwner {

    val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)

        lifecycleRegistry.markState(Lifecycle.State.CREATED)
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry.markState(Lifecycle.State.STARTED);

    }

    override fun getLifecycle(): Lifecycle {
        return super.getLifecycle()
    }
}