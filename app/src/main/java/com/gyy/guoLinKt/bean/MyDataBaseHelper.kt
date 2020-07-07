package com.gyy.guoLinKt.bean

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

class MyDataBaseHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    //注意： + 一定要在写字符串的后面，而不是换行写
    val creatBook =
        "create table book(" +
                "id Integer primary key autoincrement," +
                "author text," +
                "price real," +
                "pages Integer," +
                "names Integer)"

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(creatBook)
        Log.d(TAG, "数据库创建成功")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    companion object {
        const val TAG = "MyDataBaseHelper"
    }

}