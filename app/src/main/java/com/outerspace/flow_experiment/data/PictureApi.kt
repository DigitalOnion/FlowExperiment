package com.outerspace.flow_experiment.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.streams.toList

const val BASE_URL = "https://pixabay.com"
const val API_KEY = "8782397-0167754e846f520e8c572b2ab"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

private interface PixabayApiService {
    @GET("/api")
    suspend fun getPictures(
        @Query("per-page") perPage: Int,
        @Query("page") page: Int = 1,
        @Query("image_type") type: String = "photo",
        @Query("key") apiKey: String = API_KEY) : Response
}

private object PixabayApi {
    val retrofitService: PixabayApiService by lazy { retrofit.create(PixabayApiService::class.java)}
}

object PictureApi {
    suspend fun getPictures(size: Int = 10): List<PictureItem> {
        val response = PixabayApi.retrofitService.getPictures(size)
        val hits = response.hits
        val hitsStream = hits?.map() {hit ->
            PictureItem(hit.type?: "", hit.tags?: "", hit.previewURL?: "")
        }
        return hitsStream!!.toList()
    }
}