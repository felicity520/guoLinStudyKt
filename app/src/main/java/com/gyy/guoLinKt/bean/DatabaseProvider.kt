package com.gyy.guoLinKt.bean

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log
import androidx.core.content.contentValuesOf
import com.gyy.guoLinKt.activity.TestActivity

/***
 * 创建一个内容提供者
 */
class DatabaseProvider : ContentProvider() {

    private var dbhelper:MyDataBaseHelper? = null

    private val bookDir = 0
    private val bookItem = 1
    private val categoryDir = 2
    private val categoryItem = 3

    private val authority = "com.gyy.guoLinKt"

    //by lazy
    private val uriMatcher by lazy {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)
        matcher.addURI(authority,"book",bookDir)
        matcher.addURI(authority,"book/#",bookItem)
        matcher.addURI(authority,"category",categoryDir)
        matcher.addURI(authority,"category/#",categoryItem)
        matcher
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?) = dbhelper?.let {
        val db  = dbhelper?.writableDatabase
        val deleteid = when(uriMatcher.match(uri)){
            bookDir->{
                db?.delete("book", selection, selectionArgs)
            }
            bookItem ->{
                val bookid = uri.pathSegments[1]
                db?.delete("book", "id = ?", arrayOf(bookid))
             }
             categoryDir ->{
                 db?.delete("category", selection, selectionArgs)
           }
            categoryItem ->{
                val categoryid = uri.pathSegments[1]
                db?.delete("category", "id = ?", arrayOf(categoryid))
            }
            else -> 0
        }
        deleteid
    }?:0

    override fun getType(uri: Uri) = when(uriMatcher.match(uri)){
        bookDir->"vnd.android.cursor.dir/vnd.com.gyy.guoLinKt.book"
        bookItem->"vnd.android.cursor.item/vnd.com.gyy.guoLinKt.book"
        categoryDir->"vnd.android.cursor.dir/vnd.com.gyy.guoLinKt.category"
        categoryItem->"vnd.android.cursor.item/vnd.com.gyy.guoLinKt.category"
        else->null
    }

    override fun insert(uri: Uri, values: ContentValues?) = dbhelper?.let{
        val db  = dbhelper?.writableDatabase
        val uriReturn = when (uriMatcher.match(uri)){
            bookDir,bookItem->{
                val bookid = db?.insert("Book",null,values)
                Uri.parse("content://$authority/book/$bookid")
            }
            categoryDir,categoryItem->{
                val categoryid = db?.insert("Category",null,values)
                Uri.parse("content://$authority/category/$categoryid")
            }
            else->null
        }
        uriReturn
    }

    //false表示创建失败，true表示创建成功
    override fun onCreate() = context.let{
        dbhelper = MyDataBaseHelper(it, "BookStore.db", 2)
        true
    }?:false


    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ) = dbhelper.let {
        val db  = dbhelper?.writableDatabase
        val cursor = when(uriMatcher.match(uri)){
            bookDir->{
                db?.query("Book", projection, selection, selectionArgs, null, null, sortOrder)
            }
            bookItem->{
                val bookid = uri.pathSegments[1]
                db?.query("Book", projection, "id = ?", arrayOf(bookid), null, null, sortOrder)
            }
            categoryDir->{
                db?.query("Category", projection, selection, selectionArgs, null, null, sortOrder)
            }
            categoryItem->{
                val bookid = uri.pathSegments[1]
                db?.query("Category", projection, "id = ?", arrayOf(bookid), null, null, sortOrder)
            }
            else->null
        }
        //注意：这里一定不能关闭游标
//        cursor?.close()
        cursor
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ) =  dbhelper?.let{
        val db  = dbhelper?.writableDatabase
        val updateid = when (uriMatcher.match(uri)){
            bookDir->{
               db?.update("Book",values,selection,selectionArgs)
            }
            bookItem->{
                val bookitem = uri.pathSegments[1]
                db?.update("Book",values,"id = ?",arrayOf(bookitem))
            }
            categoryDir->{
                db?.update("Category",values,selection,selectionArgs)
            }
            categoryItem->{
                val categoryitem = uri.pathSegments[1]
                db?.update("Category",values,"id = ?",arrayOf(categoryitem))
            }
            else->null
        }
        updateid
    }?:0



}
