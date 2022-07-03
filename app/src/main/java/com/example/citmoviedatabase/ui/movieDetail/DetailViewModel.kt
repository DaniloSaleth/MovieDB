package com.example.citmoviedatabase.ui.movieDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.citmoviedatabase.model.Cast.Cast
import com.example.citmoviedatabase.model.Image.Backdrops
import com.example.citmoviedatabase.model.Movie.Movie
import com.example.citmoviedatabase.repository.Repository
import com.example.citmoviedatabase.repository.RepositoryStatus
import kotlinx.coroutines.launch

class DetailViewModel(val repository : Repository) : ViewModel(){

    private val _currentMovie = MutableLiveData<Movie>()
    val currentMovie : LiveData<Movie>
        get() = _currentMovie

    private val _error = MutableLiveData<Throwable>()
    val error : LiveData<Throwable>
        get() = _error

    fun getMovie(movieId : Int) = viewModelScope.launch{
        repository.getMovieById(movieId).apply{
            when(this){
                is RepositoryStatus.MovieSuccess ->{
                    _currentMovie.value = response
                }
                is RepositoryStatus.Error ->{
                    _error.value = response
                }
            }
        }
    }

    private val _currentCast = MutableLiveData<List<Cast>>()
    val currentCast: LiveData<List<Cast>>
        get() = _currentCast

    fun getCast(movieId: Int) = viewModelScope.launch{
        repository.getCast(movieId). apply{
            when(this){
                is RepositoryStatus.CastSuccess ->{
                    _currentCast.value = response
                }
                is RepositoryStatus.Error ->{
                    _error.value = response
                }
            }
        }
    }

    private val _currentImage = MutableLiveData<List<Backdrops>>()
    val currentImage: LiveData<List<Backdrops>>
        get() = _currentImage

    fun getImages(movieId: Int) = viewModelScope.launch{
        repository.getImages(movieId).apply{
            when(this){
                is RepositoryStatus.ImageSuccess ->{
                    _currentImage.value = response
                }
                is RepositoryStatus.Error ->{
                    _error.value = response
                }
            }
        }
    }
}