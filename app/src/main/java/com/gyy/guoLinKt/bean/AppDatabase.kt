package com.gyy.guoLinKt.bean

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gyy.guoLinKt.kotlin.Book
import com.gyy.guoLinKt.kotlin.BookDao
import com.gyy.guoLinKt.kotlin.User
import com.gyy.guoLinKt.kotlin.UserDao

/***
 *room的数据库
 */
@Database(version = 3, entities = [User::class, Book::class])
abstract class AppDatabase : RoomDatabase() {

    //一定要声明获取Dao的实例
    abstract fun UserDao(): UserDao

    abstract fun BookDao(): BookDao

    companion object {
        private lateinit var instance: AppDatabase

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("create table Book (id integer primary key autoincrement not null, name text not null, pages integer not null)")
            }
        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table Book add column author text not null default 'unknown'")
            }
        }

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            instance.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .build().apply {
                    return this
                }
        }

    }
}