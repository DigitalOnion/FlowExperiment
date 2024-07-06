package com.outerspace.flow_experiment.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

const val DATABASE_NAME = "picturedatabase"
@Module
@InstallIn(ViewModelComponent::class)
object PictureRepoModule {

    @Provides
    fun providePictureDatabase(@ApplicationContext appContext: Context): PictureDatabase =
        Room.databaseBuilder(appContext,PictureDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providepicturedao(db:PictureDatabase)=db.pictureDao()

}