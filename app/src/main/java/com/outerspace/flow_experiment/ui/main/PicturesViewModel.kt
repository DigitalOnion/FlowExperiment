package com.outerspace.flow_experiment.ui.main

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.outerspace.flow_experiment.data.PictureApi
import com.outerspace.flow_experiment.data.PictureItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

class PicturesViewModel: ViewModel() {

    val picturesState: SnapshotStateList<PictureItem> = mutableStateListOf()

    suspend fun fetchPictures(subject: String, page: Int, count: Int) {
        picturesState.clear()
        val list: List<PictureItem> = PictureApi.getPictures(subject, page, count)
        picturesState.addAll(list)
    }

    suspend fun collectPictures(subject: String, page: Int, perPage: Int) {
        PictureApi.getPictureFlow(subject, page, perPage)
            .flowOn(Dispatchers.IO)
            .catch {
                picturesState.clear()
            }
            .collect { list: List<PictureItem> ->
                picturesState.clear()
                picturesState.addAll(list)


            }
    }
}
