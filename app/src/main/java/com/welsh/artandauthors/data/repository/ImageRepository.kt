package com.welsh.artandauthors.data.repository

import com.welsh.artandauthors.common.Response
import com.welsh.artandauthors.data.domain.Image
import com.welsh.artandauthors.data.domain.PicsumApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val picsumApi: PicsumApi
) {

    suspend fun getImages() : List<Image>{
        return picsumApi.getImages()
    }

    fun getAllImages() : Flow<Response<List<Image>>> = flow {
        try {
            emit(Response.Loading())
            val images = getImages()
            emit(Response.Success(images))
        } catch (e : HttpException){
            emit(Response.Error(e.localizedMessage!!))
        }
    }
}