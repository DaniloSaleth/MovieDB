package com.example.citmoviedatabase.ui.movieDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.citmoviedatabase.di.viewModels
import com.example.citmoviedatabase.model.Cast.Cast
import com.example.citmoviedatabase.model.Image.Backdrops
import com.example.citmoviedatabase.model.Image.ListOfImages
import com.example.citmoviedatabase.model.Movie.Movie
import com.example.citmoviedatabase.repository.Repository
import com.example.citmoviedatabase.repository.RepositoryStatus
import com.example.citmoviedatabase.ui.home.HomeViewModel
import com.example.citmoviedatabase.ui.home.HomeViewModelTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val repository = RepositoryTest()
    private lateinit var viewModel: DetailViewModel

    private val movieId = 1
    private val movie = Movie(1,null,"rambo","teste",3.1,"teste","Rambo","22/01/1999", arrayOf(1,2,3),false,2.1,10,null,null,null)
    private val error : Throwable = UnknownError()
    private val castList = listOf(Cast(1,"Joao","Ninja","path123"),Cast(2,"Joao2","Ninja","path123"),Cast(3,"Joao3","Ninja","path123"))
    private val imageList = listOf(Backdrops("bkd1"), Backdrops("bkd2"),Backdrops("bkd3"))

    class RepositoryTest : Repository {
        var status : RepositoryStatus = RepositoryStatus.NotFound
        override fun getMovieById(movieId: Int, callback: (RepositoryStatus) -> Unit) {
            callback(status)
        }

        override fun getCast(movieId: Int, callback: (RepositoryStatus) -> Unit) {
            callback(status)
        }

        override fun getImages(movieId: Int, callback: (RepositoryStatus) -> Unit) {
            callback(status)
        }

        override fun getUpcoming(callback: (RepositoryStatus) -> Unit) {
            callback(status)
        }

        override fun getNowPlaying(callback: (RepositoryStatus) -> Unit)  {
            callback(status)
        }
    }

    @Before
    fun setUp() {
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun `When detail view model getMovie is Success`() {
        repository.status = RepositoryStatus.MovieSuccess(movie)

        viewModel.getMovie(movieId)

        val status = viewModel.currentMovieStatus.value
        when(status){
            is DetailViewModelSealed.ShowMovie -> {
                assertEquals(status.response, movie)
            }
            else -> {
                fail()
            }
        }
    }

    @Test
    fun `When detail view model getMovie is Error`() {
        repository.status = RepositoryStatus.Error(error)

        viewModel.getMovie(movieId)

        val status = viewModel.currentMovieStatus.value
        when(status){
            is DetailViewModelSealed.Error -> {
                assertEquals(status.error, error)
            }
            else -> {
                fail()
            }
        }
    }

    @Test
    fun `When detail view model getMovie is NotFound`() {
        repository.status = RepositoryStatus.NotFound

        viewModel.getMovie(movieId)

        val status = viewModel.currentMovieStatus.value
        when(status){
            is DetailViewModelSealed.Initial -> {
                assertEquals(status, DetailViewModelSealed.Initial)
            }
            else -> {
                fail()
            }
        }
    }

    @Test
    fun `When detail view model getCast is Success`(){
        repository.status = RepositoryStatus.CastSuccess(castList)

        viewModel.getCast(movieId)

        val status = viewModel.currentCastStatus.value
        when(status){
            is DetailViewModelSealed.ShowCast -> {
                assertEquals(status.response, castList)
            }
            else -> {
                fail()
            }
        }
    }

    @Test
    fun `When detail view model getCast is Error`(){
        repository.status = RepositoryStatus.Error(error)

        viewModel.getCast(movieId)

        val status = viewModel.currentCastStatus.value
        when(status){
            is DetailViewModelSealed.Error -> {
                assertEquals(status.error, error)
            }
            else -> {
                fail()
            }
        }
    }

    @Test
    fun `When detail view model getCast is NotFound`(){
        repository.status = RepositoryStatus.NotFound

        viewModel.getCast(movieId)

        val status = viewModel.currentCastStatus.value
        when(status){
            is DetailViewModelSealed.Initial -> {
                assertEquals(status, DetailViewModelSealed.Initial)
            }
            else -> {
                fail()
            }
        }
    }

    @Test
    fun `When detail view model getImage is Success`(){
        repository.status = RepositoryStatus.ImageSuccess(imageList)

        viewModel.getImages(movieId)

        val status = viewModel.currentImageStatus.value
        when(status){
            is DetailViewModelSealed.ShowImages -> {
                assertEquals(status.response, imageList)
            }
            else -> {
                fail()
            }
        }
    }

    @Test
    fun `When detail view model getImage is Error`(){
        repository.status = RepositoryStatus.Error(error)

        viewModel.getImages(movieId)

        val status = viewModel.currentImageStatus.value
        when(status){
            is DetailViewModelSealed.Error -> {
                assertEquals(status.error, error)
            }
            else -> {
                fail()
            }
        }
    }

    @Test
    fun `When detail view model getImage is NotFound`(){
        repository.status = RepositoryStatus.NotFound

        viewModel.getImages(movieId)

        val status = viewModel.currentImageStatus.value
        when(status){
            is DetailViewModelSealed.Initial -> {
                assertEquals(status, DetailViewModelSealed.Initial)
            }
            else -> {
                fail()
            }
        }
    }
}