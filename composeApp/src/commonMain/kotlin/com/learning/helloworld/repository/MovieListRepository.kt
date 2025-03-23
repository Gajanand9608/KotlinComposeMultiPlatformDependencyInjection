package com.learning.helloworld.repository

import com.learning.helloworld.remote.MovieListApi
import com.learning.helloworld.util.NetworkError
import com.learning.helloworld.util.Result
import com.learning.helloworld.remote.dto.MoviesListResponse

class MovieListRepository(private val movieListApi: MovieListApi) {

    suspend fun getMoviesList(apiKey: String, page: Int): Result<MoviesListResponse, NetworkError> {
        android.util.Log.d("Gajanand", "getMoviesList: 3")
        return movieListApi.getMoviesList(apiKey, page)
    }
}