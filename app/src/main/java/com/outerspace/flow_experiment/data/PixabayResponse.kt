package com.outerspace.flow_experiment.data

@Suppress("Unused")
class Hit {
    @com.squareup.moshi.Json(name = "id")
    var id: Int? = null

    @com.squareup.moshi.Json(name = "pageURL")
    var pageURL: String? = null

    @com.squareup.moshi.Json(name = "type")
    var type: String? = null

    @com.squareup.moshi.Json(name = "tags")
    var tags: String? = null

    @com.squareup.moshi.Json(name = "previewURL")
    var previewURL: String? = null

    @com.squareup.moshi.Json(name = "previewWidth")
    var previewWidth: Int? = null

    @com.squareup.moshi.Json(name = "previewHeight")
    var previewHeight: Int? = null

    @com.squareup.moshi.Json(name = "webformatURL")
    var webFormatURL: String? = null

    @com.squareup.moshi.Json(name = "webformatWidth")
    var webFormatWidth: Int? = null

    @com.squareup.moshi.Json(name = "webformatHeight")
    var webFormatHeight: Int? = null

    @com.squareup.moshi.Json(name = "largeImageURL")
    var largeImageURL: String? = null

    @com.squareup.moshi.Json(name = "imageWidth")
    var imageWidth: Int? = null

    @com.squareup.moshi.Json(name = "imageHeight")
    var imageHeight: Int? = null

    @com.squareup.moshi.Json(name = "imageSize")
    var imageSize: Int? = null

    @com.squareup.moshi.Json(name = "views")
    var views: Int? = null

    @com.squareup.moshi.Json(name = "downloads")
    var downloads: Int? = null

    @com.squareup.moshi.Json(name = "collections")
    var collections: Int? = null

    @com.squareup.moshi.Json(name = "likes")
    var likes: Int? = null

    @com.squareup.moshi.Json(name = "comments")
    var comments: Int? = null

    @com.squareup.moshi.Json(name = "user_id")
    var userId: Int? = null

    @com.squareup.moshi.Json(name = "user")
    var user: String? = null

    @com.squareup.moshi.Json(name = "userImageURL")
    var userImageURL: String? = null
}

@Suppress("Unused")
class Response {
    @com.squareup.moshi.Json(name = "total")
    var total: Int? = null

    @com.squareup.moshi.Json(name = "totalHits")
    var totalHits: Int? = null

    @com.squareup.moshi.Json(name = "hits")
    var hits: List<Hit>? = null
}