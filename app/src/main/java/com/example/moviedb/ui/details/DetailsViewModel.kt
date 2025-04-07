package com.example.moviedb.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedb.model.movie.Movie
import com.example.moviedb.repository.details.DetailsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailsViewModel(
    val repository: DetailsRepository
) : ViewModel() {
    private val _state = MutableLiveData<DetailsState>()
    val state: LiveData<DetailsState> = _state

    fun getMovieDetails(movieId: Int) {
        _state.value = DetailsState.Loading
        viewModelScope.launch {
            repository.getMovieDetails(movieId).onStart {
                setLoadingState()
            }.catch {
                setErrorState()
            }.collect { movie ->
                setSuccessState(movie)
            }
        }
    }

    private fun setErrorState() {
        _state.value = DetailsState.Error
    }

    private fun setLoadingState() {
        _state.value = DetailsState.Loading
    }

    private fun setSuccessState(movie: Movie) {
        _state.value = DetailsState.Success(movie)
    }
}