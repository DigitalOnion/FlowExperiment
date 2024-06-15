package com.outerspace.flow_experiment.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface PictureApiInterface {
    suspend fun getPictures(subject: String, page: Int, perPage: Int): List<PictureItem>

    fun getPictureFlow(subject: String, page: Int, perPage: Int): Flow<List<PictureItem>>
}

object PictureApi: PictureApiInterface  {
    override suspend fun getPictures(subject: String, page: Int, perPage: Int): List<PictureItem> {
        val response = pixabayApiService.getPictures(q = subject, page = page, perPage = perPage)
        val pictureList:List<PictureItem> = response.hits?.map {
            PictureItem(type = it.type?: "", tags = it.tags?: "", url = it.previewURL?: "")
        } ?: emptyList()
        return pictureList
    }

    override fun getPictureFlow(
        subject: String,
        page: Int,
        perPage: Int
    ): Flow<List<PictureItem>> = flow {
        emit(
            getPictures(subject, page, perPage)     // the same getPictures embedded in the flow's emit
        )
    }
}