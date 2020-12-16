package com.gyy.guoLinKt.kotlin

/**
 * 枚举中，如果有方法的情况，必须用；来分割枚举常量列表和方法
 */
enum class Color {
    RED,
    BLACK,
    BLUE,
    GREEN,
    WHITE;

    fun getTopColor(): Color {
        return BLACK
    }
}