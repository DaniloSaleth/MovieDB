package com.example.citmoviedatabase.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.citmoviedatabase.model.Movie.Movie
import com.example.citmoviedatabase.repository.Repository
import com.example.citmoviedatabase.repository.RepositoryStatus
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import junit.framework.Assert.assertEquals
import junit.framework.Assert.fail

class HomeViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    //When Succes return a list of Movies
    val listMovie : List<Movie> = listOf(
        Movie(1,null,"rambo","teste",3.1,"teste","Rambo","22/01/1999", arrayOf(1,2,3),false,2.1,10,null,null,null),
        Movie(1,null,"rambo2","teste",3.1,"teste","Rambo","22/01/1999", arrayOf(1,2,3),false,2.1,10,null,null,null),
        Movie(1,null,"rambo3","teste",3.1,"teste","Rambo","22/01/1999", arrayOf(1,2,3),false,2.1,10,null,null,null)
    )

    //When Error return a Throwable
    val error : Throwable = UnknownError()

    private val repository = RepositoryTest()
    private lateinit var viewModel: HomeViewModel

    class RepositoryTest : Repository{
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
    fun setUp(){
        viewModel = HomeViewModel(repository)
    }

    @Test
    fun `When view model now playing is success`(){

        //Mockando repository com valor de sucesso
        repository.status = RepositoryStatus.MovieListSuccess(listMovie)

        //Metodo sendo testado
        viewModel.getNowPlaying()

        //Verificando se os valores são iguais
        val status = viewModel.currentStatus.value
        when(status){
            is HomeViewModelSealed.ShowMovies ->{
                assertEquals(status.movieList,listMovie)
            }
            else ->{
                fail()
            }
        }
    }

    @Test
    fun `When view model now playing is Error`(){
        repository.status = RepositoryStatus.Error(error)

        viewModel.getNowPlaying()

        val status = viewModel.currentStatus.value
        when(status){
            is HomeViewModelSealed.Error ->{
                assertEquals(status.error , error)
            }
            else ->{
                fail()
            }
        }
    }

    @Test
    fun `When view model now playin is NotFound`(){
        repository.status = RepositoryStatus.NotFound

        viewModel.getNowPlaying()

        val status = viewModel.currentStatus.value
        when(status){
            is HomeViewModelSealed.Initial -> {
                assertEquals(status, HomeViewModelSealed.Initial)
            }
            else ->{
                fail()
            }
        }
    }

    @Test
    fun `When view model upcoming is success`(){
        repository.status = RepositoryStatus.MovieListSuccess(listMovie)

        viewModel.getUpcoming()

        val status = viewModel.currentStatus.value
        when(status){
            is HomeViewModelSealed.ShowMovies ->{
                assertEquals(status.movieList,listMovie)
            }
            else ->{
                fail()
            }
        }
    }

    @Test
    fun `When view model upcoming is Error`(){
        repository.status = RepositoryStatus.Error(error)

        viewModel.getUpcoming()

        val status = viewModel.currentStatus.value
        when(status){
            is HomeViewModelSealed.Error ->{
                assertEquals(status.error,error)
            }
            else ->{
                fail()
            }
        }
    }

    @Test
    fun `When view model upcoming is NotFound`(){
        repository.status = RepositoryStatus.NotFound

        viewModel.getUpcoming()

        val status = viewModel.currentStatus.value
        when(status){
            is HomeViewModelSealed.Initial ->{
                assertEquals(status, HomeViewModelSealed.Initial)
            }
            else ->{
                fail()
            }
        }
    }
}