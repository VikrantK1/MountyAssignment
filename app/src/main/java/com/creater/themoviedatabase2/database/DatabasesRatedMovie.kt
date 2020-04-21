package com.creater.themoviedatabase2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
/** Database of TopRatedMovies **/
@Database(entities = arrayOf(DatabaseClass::class),version = 1)


    abstract class DatabasesRatedMovie: RoomDatabase() {
        var instance:DatabasesRatedMovie?=null
        abstract fun imagDao():ImgdataDao
        companion object {
            @Volatile private var INSTANCE:DatabasesRatedMovie?=null
            val Lock=Any()
            operator  fun invoke(context: Context)= INSTANCE?: synchronized(Lock){
                INSTANCE?: buldDatabase(context).also { INSTANCE=it }
            }
            fun buldDatabase(context: Context)=
                Room.databaseBuilder(context.applicationContext,DatabasesRatedMovie::class.java,"DatabaseTop").build()
        }


    }
