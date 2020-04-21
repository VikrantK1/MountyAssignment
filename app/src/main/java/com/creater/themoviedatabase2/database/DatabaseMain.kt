package com.creater.themoviedatabase2.database

import android.content.Context
import androidx.room.*
import androidx.room.RoomDatabase.Callback
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = arrayOf(DatabaseClass::class),version = 1)
 abstract class DatabaseMain: RoomDatabase() {
    var instance:DatabaseMain?=null
     abstract fun imagDao():ImgdataDao
    companion object {
       @Volatile private var INSTANCE:DatabaseMain?=null
        val Lock=Any()
        operator  fun invoke(context: Context)= INSTANCE?: synchronized(Lock){
            INSTANCE?: buldDatabase(context).also { INSTANCE=it }
        }
        fun buldDatabase(context: Context)=
            Room.databaseBuilder(context.applicationContext,DatabaseMain::class.java,"Database").build()
    }


}