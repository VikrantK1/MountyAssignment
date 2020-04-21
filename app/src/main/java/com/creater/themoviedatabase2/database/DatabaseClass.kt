package com.creater.themoviedatabase2.database

import android.graphics.Bitmap
import android.icu.text.CaseMap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/** Database data container class **/
@Entity(tableName = "MovieData")
data class DatabaseClass(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 1,
    var title: String? = null,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var imgdata: ByteArray? = null
) {

}
