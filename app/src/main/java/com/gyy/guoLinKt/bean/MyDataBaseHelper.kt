package com.gyy.guoLinKt.bean

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDataBaseHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    val creatTable = "create table book(" + "id Integer primary key autoincrement,"
    
    override fun onCreate(p0: SQLiteDatabase?) {

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

}