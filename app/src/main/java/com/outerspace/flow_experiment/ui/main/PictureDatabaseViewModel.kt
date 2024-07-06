package com.outerspace.flow_experiment.ui.main

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.outerspace.flow_experiment.data.PictureEntity
import com.outerspace.flow_experiment.data.PictureItem
import com.outerspace.flow_experiment.data.PictureRepository
import com.outerspace.flow_experiment.data.PixabayApiService
import com.outerspace.flow_experiment.data.pixabayApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PictureDatabaseViewModel @Inject constructor(private val pictureRepository: PictureRepository) :
    ViewModel() {


    var picturesentityState: SnapshotStateList<PictureEntity> = mutableStateListOf()
    suspend fun getall(): List<PictureEntity> = pictureRepository.getallPictures()
    suspend fun deleteall() = pictureRepository.deletepictures()
    suspend fun insertpictures(pictureEntity: List<PictureEntity>) =
        pictureRepository.insertpictures(pictureEntity)

    suspend fun getallpicture(subject: String, page: Int, perPage: Int): List<PictureEntity> {

        val response = pixabayApiService.getPictures(q = subject, page = page, perPage = perPage)
        val pictureList: List<PictureEntity> = response.hits?.map {
            PictureEntity(
                null,
                type = it.type ?: "",
                tags = it.tags ?: "",
                url = it.previewURL ?: ""
            )
        } ?: emptyList()
        for (picture in pictureList) {
            deleteall()
            insertpictures(pictureList)
            picturesentityState.addAll(pictureList)
        }
        return pictureList
    }


}