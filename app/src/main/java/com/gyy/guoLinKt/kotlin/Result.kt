package com.gyy.guoLinKt.kotlin

sealed class Result

class Success(val msg: String) : Result()

class Unknow(val msg: String) : Result()

class Failure(val error: Exception) : Result()

