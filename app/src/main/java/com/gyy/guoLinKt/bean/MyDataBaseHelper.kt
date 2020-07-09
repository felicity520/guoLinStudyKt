package com.gyy.guoLinKt.bean

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

class MyDataBaseHelper(
    context: Context?,
    name: String?,
    version: Int
) : SQLiteOpenHelper(context, name, null, version) {

    //注意： + 一定要在写字符串的后面，而不是换行写   必须是integer而不是Integer
    val creatBook =
        "create table Book(" +
                "id integer primary key autoincrement," +
                "author text," +
                "price real," +
                "pages integer," +
                "names text," +
                "category_id integer," +
                "category_name integer)"

    val creatCategory =
        "create table Category(" +
                "id integer primary key autoincrement," +
                "category_name text," +
                "category_code integer)"

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(creatBook)
        //如果新用户第一次安装则创建Category表
        p0?.execSQL(creatCategory)
        Log.d(TAG, "数据库创建成功")
    }

    //p1是旧版本，p2是新版本，这是不会变的
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        //这种方式简单粗暴
//        p0?.execSQL("drop table if exists Book")
//        p0?.execSQL("drop table if exists Category")
//        onCreate(p0)
        Log.d(TAG, "数据库升级成功")

        if (p1 <= 1) {
            p0?.execSQL(creatCategory)
            Log.d(TAG, "数据库从$p1 到 $p2 升级成功")
        }
        if (p1 <= 2) {
            //ALTER TABLE table_name
            //ADD column_name datatype
            p0?.execSQL("alter table Book add column category_id integer")
            Log.d(TAG, "数据库从$p1 到 $p2 升级成功")
        }
        if (p1 <= 6) {
            //ALTER TABLE table_name
            //ADD column_name datatype
            p0?.execSQL("alter table Book add column category_name integer")
            Log.d(TAG, "数据库从$p1 到 $p2 升级成功")
        }
    }

    companion object {
        const val TAG = "MyDataBaseHelper"
    }

}