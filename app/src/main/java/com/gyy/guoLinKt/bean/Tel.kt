package com.gyy.guoLinKt.bean

data class Tel(
    val msg: String,
    val result: Result,
    val status: Int
)

data class Result(
    val cardtype: Any,
    val city: String,
    val company: String,
    val province: String,
    val shouji: String
)