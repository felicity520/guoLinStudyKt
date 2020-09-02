package com.gyy.guoLinKt.bean

import android.content.Context
import androidx.startup.AppInitializer
import androidx.startup.Initializer

/***
 * 郭神的startup:https://mp.weixin.qq.com/s/rverE0OGRnncB5-K-_Wesg
 */
class LitePalInitializer : Initializer<Unit> {

    override fun create(context: Context) {
//        LitePal.initialize(context)
        //执行库的初始化操作

        //延迟初始化之后，调用这段即可完成同样的初始化操作
//        AppInitializer.getInstance(this)
//            .initializeComponent(LitePalInitializer::class.java)

    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
//        return listOf(OtherInitializer::class.java)
        return emptyList()
    }

}