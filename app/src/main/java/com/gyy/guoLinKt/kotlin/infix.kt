package com.gyy.guoLinKt.kotlin

infix fun String.beginsWith(prefix: String) = startsWith(prefix)

//前面加泛型表示方法
infix fun <T> Collection<T>.has(that: T) = contains(that)

infix fun <A, B> A.with(that: B): Pair<A, B> = Pair(this, that)