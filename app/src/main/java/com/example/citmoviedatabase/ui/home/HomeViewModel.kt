package com.example.citmoviedatabase.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.citmoviedatabase.MyApp
import com.example.citmoviedatabase.model.Genre.Genre
import com.example.citmoviedatabase.model.Movie.ListOfMovie
import com.example.citmoviedatabase.model.Movie.Movie
import com.example.citmoviedatabase.repository.Repository
import com.example.citmoviedatabase.repository.RepositoryDB.RepositoryDB
import com.example.citmoviedatabase.repository.RepositoryDB.RepositoryDBStatus
import com.example.citmoviedatabase.repository.RepositoryStatus
import kotlinx.coroutines.launch

class HomeViewModel(private val repository : Repository,private val repositoryDB: RepositoryDB) : ViewModel() {

    private val _currentMsg = MutableLiveData<String>()
    val currentMsg: LiveData<String>
        get() = _currentMsg

    private val _favorites = MutableLiveData<List<Movie>>()
    val favorites : LiveData<List<Movie>>
        get() = _favorites

    private val _currentMovieList = MutableLiveData<ListOfMovie>()
    val currentMovieList: LiveData<ListOfMovie>
        get() = _currentMovieList

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

    fun getNowPlaying(pageNum : Int) = viewModelScope.launch{
        repository.getNowPlaying(pageNum).apply {
            when(this){
                is RepositoryStatus.MovieListSuccess -> {
                    _currentMovieList.value = response
                }
                is RepositoryStatus.Error -> {
                    _error.value = response
                }
            }
        }
    }

    fun getFavorites() = viewModelScope.launch{
        repositoryDB.getFavorites().apply {
            when(this){
                is RepositoryDBStatus.FavoritesSuccess -> {
                    _currentMovieList.value = ListOfMovie(1,response as ArrayList<Movie>,1)
                }
                is RepositoryDBStatus.Error -> {
                    _error.value = response
                }
            }
        }
    }

    fun refreshFavorites() = viewModelScope.launch{
        repositoryDB.getFavorites().apply {
            when(this){
                is RepositoryDBStatus.FavoritesSuccess -> {
                   _favorites.value = response
                }
                is RepositoryDBStatus.Error -> {
                    _error.value = response
                }
            }
        }
    }

    fun setFavorite(movie : Movie) = viewModelScope.launch{
        repositoryDB.setFavorite(movie).apply {
            when(this){
                is RepositoryDBStatus.InsertListSuccess -> {
                    _currentMsg.value = response
                }
                is RepositoryDBStatus.Error -> {
                    _error.value = response
                }
            }
        }
    }

    fun removeFavorite(movie : Movie) = viewModelScope.launch{
        repositoryDB.removeFavorite(movie).apply {
            when(this){
                is RepositoryDBStatus.InsertListSuccess -> {
                    _currentMsg.value = response
                }
                is RepositoryDBStatus.Error -> {
                    _error.value = response
                }
            }
        }
    }

    fun getUpcoming(pageNum : Int) = viewModelScope.launch{
        repository.getUpcoming(pageNum).apply {
            when(this){
                is RepositoryStatus.MovieListSuccess -> {
                    _currentMovieList.value = response
                }
                is RepositoryStatus.Error -> {
                    _error.value = response
                }
            }
        }
    }

    fun getPopular(pageNum : Int) = viewModelScope.launch{
        repository.getPopular(pageNum).apply {
            when(this){
                is RepositoryStatus.MovieListSuccess -> {
                    _currentMovieList.value = response
                }
                is RepositoryStatus.Error -> {
                    _error.value = response
                }
            }
        }
    }

    fun getMovieByName(movieName : String, pageNum : Int) = viewModelScope.launch{
        repository.getMovieByName(movieName,pageNum).apply {
            when(this){
                is RepositoryStatus.MovieListSuccess -> {
                    _currentMovieList.value = response
                }
                is RepositoryStatus.Error -> {
                    _error.value = response
                }
            }
        }
    }

    private val _listGenres = MutableLiveData<List<Genre>>()
    val listGenres : LiveData<List<Genre>>
        get() = _listGenres

    fun getGenres() = viewModelScope.launch {
        repository.getGenres().apply {
            when(this){
                is RepositoryStatus.GenreSuccess -> {
                    _listGenres.value = response
                }
                is RepositoryStatus.Error -> {
                    _error.value = response
                }
            }
        }
    }
}
