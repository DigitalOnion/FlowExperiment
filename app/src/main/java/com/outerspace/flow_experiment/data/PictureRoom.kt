package com.outerspace.flow_experiment.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

@Dao
interface PictureDao {

    @Insert
    suspend fun insertPictures(picture: List<PictureEntity>): List<Long>

    @Update(entity = PictureEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePictures(picture: PictureEntity)

    @Query("select * from pictures")
    suspend fun getAllData(): List<PictureEntity>

    @Query("delete from pictures")
    suspend fun deleteAll()

}

@Database(entities = [PictureEntity::class], version = 2, exportSchema = false)

abstract class PictureDatabase : RoomDatabase() {
    abstract fun pictureDao(): PictureDao

}