package com.gyy.guoLinKt.kotlin

class Money(val value: Int) {

    operator fun plus(money: Money): Int {
        return value + money.value
    }

    operator fun plus(value1: Int): Int {
        return value + value1
    }
}