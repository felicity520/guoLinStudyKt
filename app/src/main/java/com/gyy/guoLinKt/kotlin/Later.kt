package com.gyy.guoLinKt.kotlin

import kotlin.reflect.KProperty

//泛型类
class Later<T>(val block: () -> T) {
    var value: Any? = null

    operator fun getValue(any: Any?, prop: KProperty<*>): T {
        if (value == null) {
            value = block()
        }
        return value as T
    }
}

//泛型方法
//block: () -> T  表示一个函数类型参数，()表示参数任意，T表示返回
fun <T> later(block: () -> T) = Later(block)