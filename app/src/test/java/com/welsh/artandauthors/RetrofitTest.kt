package com.welsh.artandauthors

import com.welsh.artandauthors.common.Constants.BASE_URL
import com.welsh.artandauthors.common.Response
import com.welsh.artandauthors.data.domain.Image
import com.welsh.artandauthors.data.repository.ImageRepository
import com.welsh.artandauthors.ui.imagelist.ImagesViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientTest {

    @MockK
    private lateinit var viewModel: ImagesViewModel

    @MockK
    private lateinit var imagesRepo: ImageRepository

    @ExperimentalCoroutinesApi
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        coEvery { imagesRepo.getImages() } returns listOf()
        coEvery {
            viewModel.imageRepository.getAllImages()
        } returns flow {
            Response.Success<List<Image>>(listOf())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun runError(): Unit = runTest {
        coEvery {
            viewModel.imageRepository.getAllImages()
        } returns flow {
            emit(Response.Error(message = "Error"))
        }
        viewModel.imageRepository.getAllImages().collect{
            assert(it.message?.isNotEmpty() == true)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun runSuccess(): Unit = runTest {
        coEvery {
            viewModel.imageRepository.getAllImages()
        } returns flow {
            emit(Response.Success(returnImages()))
        }
        viewModel.imageRepository.getAllImages().collect {
            assert(it.data?.isNotEmpty() == true)
        }
    }

    fun returnImages(): List<Image> {
        return listOf(
            Image(
                author = "Kristy Welsh",
                downloadUrl = "https://picsum.photos/id/0/5616/3744"
            ),
            Image(author = "Kristy Welsh", downloadUrl = "https://picsum.photos/id/1/5616/3744")
        )
    }

    @Test
    fun testRetrofitInstance() {
        //Get an instance of Retrofit
        val instance: Retrofit = getRetrofit()
        //Assert that, Retrofit's base url matches to our BASE_URL
        assert(instance.baseUrl().toUrl().toString() == BASE_URL)
    }

    fun getRetrofit(): Retrofit  {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
