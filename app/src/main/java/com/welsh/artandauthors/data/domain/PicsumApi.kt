package com.welsh.artandauthors.data.domain

import retrofit2.http.GET

interface PicsumApi {

    @GET("/v2/list")
    suspend fun getImages() : List<Image>
}