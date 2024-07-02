package com.outerspace.flow_experiment.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "picture")
data class PictureEntity(
    @PrimaryKey(autoGenerate = true) val id : Long? = null,

    @ColumnInfo(name = "page")val page : Int,
    @ColumnInfo(name = "per_page") val perpage : Int,
    @ColumnInfo(name = "q") val subject : String
)



