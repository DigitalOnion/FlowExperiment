package com.outerspace.flow_experiment.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "pictures")
data class PictureEntity(
    @PrimaryKey(autoGenerate = true) val id : Long? = null,

    @ColumnInfo(name = "type")val type : String,
    @ColumnInfo(name = "tags") val tags : String,
    @ColumnInfo(name = "url") val url : String
)



