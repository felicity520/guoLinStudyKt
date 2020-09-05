package com.gyy.guoLinKt.kotlin

//运算符重载的方法一
class Money(val value: Int) {

    operator fun plus(money: Money): Int {
        return value + money.value
    }

    operator fun plus(value1: Int): Int {
        return value + value1
    }
}


//运算符重载的方法二：这里用到了 给参数设置默认值
//这里参数写死了，明显上一种方法更好
data class Salary(var base: Int = 100) {
    override fun toString(): String = base.toString()
}

operator fun Salary.plus(other: Salary): Salary = Salary(base + other.base)
operator fun Salary.minus(other: Salary): Salary = Salary(base - other.base)

