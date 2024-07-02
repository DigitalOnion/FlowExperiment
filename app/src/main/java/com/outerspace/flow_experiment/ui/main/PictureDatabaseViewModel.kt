package com.outerspace.flow_experiment.ui.main

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.outerspace.flow_experiment.data.PictureEntity
import com.outerspace.flow_experiment.data.PictureItem
import com.outerspace.flow_experiment.data.PictureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PictureDatabaseViewModel @Inject constructor(private val pictureRepository: PictureRepository) :
    ViewModel() {
    val picturesState: SnapshotStateList<PictureItem> = mutableStateListOf()

    suspend fun insertpicture(pictureEntity: PictureEntity) =
        pictureRepository.insertpictures(pictureEntity)

    suspend fun getall(): List<PictureEntity> = pictureRepository.getallPictures()

    suspend fun initializepicturetable() {
        for (picture in testPeopleList()) {
            insertpicture(picture)
        }
    }

    fun testPeopleList(): List<PictureEntity> {
        return listOf(
            PictureEntity(null, 1, 2, "Gnaesha"),
            PictureEntity(null, 2, 3, "Vinayaka"),

            )
    }
}