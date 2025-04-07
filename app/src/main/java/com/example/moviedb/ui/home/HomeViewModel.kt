package com.example.moviedb.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedb.model.movie.MovieList
import com.example.moviedb.repository.home.HomeRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository
) : ViewModel() {

    private val _isNowPlaying = MutableLiveData<Boolean>()
    val isNowPlaying: LiveData<Boolean> = _isNowPlaying

    private val _state = MutableLiveData<HomeState>()
    val state: LiveData<HomeState> = _state

    fun getNowPlayingMovies() {
        _state.value = HomeState.Loading
        viewModelScope.launch {
            repository.getNowPlayingMovies()
                .onStart {
                    setLoadingState()
                }
                .catch {
                    setErrorState()
                }
                .collect { movies ->
                    _isNowPlaying.value = true
                    if (movies.results.isEmpty())
                        setEmptyState()
                    else
                        setSuccessState(movies)
                }
        }
    }

    fun getUpcomingMovies() {
        _state.value = HomeState.Loading
        viewModelScope.launch {
            repository.getUpcomingMovies()
                .onStart {
                    setLoadingState()
                }
                .catch {
                    setErrorState()
                }
                .collect { movies ->
                    _isNowPlaying.value = false
                    if (movies.results.isEmpty())
                        setEmptyState()
                    else
                        setSuccessState(movies)
                }
        }
    }

    fun getMovieByName(movieName: String) {
        _state.value = HomeState.Loading
        viewModelScope.launch {
            repository.getMovieByName(movieName)
                .onStart {
                    setLoadingState()
                }
                .catch {
                    setErrorState()
                }
                .collect { movies ->
                    _isNowPlaying.value = false
                    if (movies.results.isEmpty())
                        setEmptyState()
                    else
                        setSuccessState(movies)
                }
        }
    }

    private fun setEmptyState(){
        _state.value = HomeState.EmptyState
    }

    private fun setErrorState(){
        _state.value = HomeState.Error
    }

    private fun setLoadingState(){
        _state.value = HomeState.Loading
    }

    private fun setSuccessState(movies : MovieList){
        _state.value = HomeState.Success(movies)
    }
}