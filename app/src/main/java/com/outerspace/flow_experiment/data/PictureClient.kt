package com.outerspace.flow_experiment.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Pixabay api can be found in: https://pixabay.com/api/docs/
 */

const val BASE_URL = "https://pixabay.com"
const val API_KEY = "8782397-0167754e846f520e8c572b2ab"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface PixabayApiService {

    // the getPictures function is equivalent to:
    // $ curl "https://pixabay.com/api/?key=8782397-0167754e846f520e8c572b2ab&page=1&per_page=3&image_type=photo&q=ganesh&pretty=true"

    @GET("/api")
    suspend fun getPictures(
        @Query("key") apiKey: String = API_KEY,
        @Query("page") page: Int = 1,
        @Query("per-page") perPage: Int = 3,
        @Query("image_type") type: String = "photo",
        @Query("q") q: String = "ganesh"
    ) : Response
}

val pixabayApiService: PixabayApiService by lazy { retrofit.create(PixabayApiService::class.java) }


