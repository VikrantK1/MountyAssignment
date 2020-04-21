package com.creater.themoviedatabase2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
/** Room class interface **/
@Dao
interface ImgdataDao {
    @Insert(onConflict = REPLACE)
    fun insert(data:DatabaseClass)
    @Query("DELETE FROM MovieData")
          fun AllDelete()
    @Query("SELECT * FROM MovieData")
       fun getAllData():LiveData<List<DatabaseClass>>
}