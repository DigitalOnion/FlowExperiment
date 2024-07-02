package com.outerspace.flow_experiment.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

@Dao
interface PictureDao{

    @Insert
    suspend fun insertPictures(picture: PictureEntity)

    @Update(entity = PictureEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePictures(picture:PictureEntity)

    @Query("select * from pictures ")
    suspend fun getAllData(): List<PictureEntity>

}

@Database(entities = [PictureEntity::class], version = 1, exportSchema = false)

abstract class picturedatabase : RoomDatabase() {
    abstract fun picturedao(): PictureDao

}