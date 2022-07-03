package com.example.citmoviedatabase.repository

import com.example.citmoviedatabase.networking.Endpoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryImpl(private val endpoint : Endpoint) : Repository{

    override suspend fun getUpcoming(pageNum: Int): RepositoryStatus{
      return withContext(Dispatchers.IO){
          try {
              val listOfMovie = endpoint.getUpcoming(pageNum)
              RepositoryStatus.MovieListSuccess(listOfMovie)
          }catch (t : Throwable){
              RepositoryStatus.Error(t)
          }
      }
    }

    override suspend fun getPopular(pageNum: Int): RepositoryStatus{
        return withContext(Dispatchers.IO){
            try {
                val listOfMovie = endpoint.getPopular(pageNum)
                RepositoryStatus.MovieListSuccess(listOfMovie)
            }catch (t : Throwable){
                RepositoryStatus.Error(t)
            }
        }
    }

    override suspend fun getNowPlaying(page : Int): RepositoryStatus {
        return withContext(Dispatchers.IO){
            try {
                val listOfMovie = endpoint.getNowPlaying(page)
                RepositoryStatus.MovieListSuccess(listOfMovie)
            } catch (t : Throwable){
                RepositoryStatus.Error(t)
            }
        }
    }

    override suspend fun getImages(movieId: Int): RepositoryStatus{
        return withContext(Dispatchers.IO){
            try {
                val listOfImages = endpoint.getImages(movieId)
                RepositoryStatus.ImageSuccess(listOfImages.backdrops)
            } catch (t : Throwable){
                RepositoryStatus.Error(t)
            }
        }
    }

    override suspend fun getMovieById(movieId: Int) : RepositoryStatus{
        return withContext(Dispatchers.IO){
            try {
                val movie = endpoint.getMovieById(movieId)
                RepositoryStatus.MovieSuccess(movie)
            }catch (t : Throwable){
                RepositoryStatus.Error(t)
            }
        }
    }

    override suspend fun getMovieByName(movieName: String, pageNum: Int): RepositoryStatus {
        return withContext(Dispatchers.IO){
            try {
                val movieResponse = endpoint.getMovieByName(movieName, pageNum)
                RepositoryStatus.MovieListSuccess(movieResponse)
            }catch (t : Throwable){
                RepositoryStatus.Error(t)
            }
        }
    }

    override suspend fun getGenres(): RepositoryStatus{
        return withContext(Dispatchers.IO){
            try {
                val movieResponse = endpoint.getGenres()
                RepositoryStatus.GenreSuccess(movieResponse.genres)
            }catch (t : Throwable){
                RepositoryStatus.Error(t)
            }
        }
    }

    override suspend fun getCast(movieId: Int): RepositoryStatus{
        return withContext(Dispatchers.IO){
            try {
                val listOfCast = endpoint.getCast(movieId)
                RepositoryStatus.CastSuccess(listOfCast.cast)
            } catch (t : Throwable){
                RepositoryStatus.Error(t)
            }
        }
    }
}