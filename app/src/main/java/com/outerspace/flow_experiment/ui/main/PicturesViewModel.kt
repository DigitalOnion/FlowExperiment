package com.outerspace.flow_experiment.ui.main

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.outerspace.flow_experiment.data.PictureApi
import com.outerspace.flow_experiment.data.PictureItem

class PicturesViewModel: ViewModel() {

    val picturesState: SnapshotStateList<PictureItem> = mutableStateListOf()

    suspend fun fetchPictures(count: Int) {
        picturesState.clear()
        val list = PictureApi.getPictures(count)
        for (pictureItem: PictureItem in list) {
            picturesState.add(pictureItem)
        }
    }
}