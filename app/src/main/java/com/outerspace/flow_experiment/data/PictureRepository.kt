package com.outerspace.flow_experiment.data

import javax.inject.Inject


class PictureRepository @Inject constructor(private val dao: PictureDao) {

    suspend fun getallPictures(): List<PictureEntity> {
        return dao.getAllData()
    }

    suspend fun insertpictures(picture: List<PictureEntity>) = dao.insertPictures(picture)

    suspend fun updatepictures(picture: PictureEntity) = dao.updatePictures(picture)

    suspend fun fetchpictures() = dao.getAllData()

    suspend fun deletepictures() = dao.deleteAll()


}