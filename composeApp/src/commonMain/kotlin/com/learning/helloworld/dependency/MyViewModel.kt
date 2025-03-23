package com.learning.helloworld.dependency

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.helloworld.common.Constants
import com.learning.helloworld.common.Resource
import com.learning.helloworld.repository.MovieListRepository
import com.learning.helloworld.util.onError
import com.learning.helloworld.util.onSuccess
import com.learning.helloworld.remote.dto.MoviesListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class MyViewModel(
    private val repository: MovieListRepository
): ViewModel() {

    private val _movieListState = mutableStateOf<Resource<MoviesListResponse>>(Resource.Loading())
    val movieListState: State<Resource<MoviesListResponse>> = _movieListState

    init {
        android.util.Log.d("Gajanand", ": 1")
        getMovieData()
    }

    fun getMovieData() {
        android.util.Log.d("Gajanand", ": 2")
        viewModelScope.launch(Dispatchers.IO) {
            _movieListState.value = Resource.Loading()
            val response = try {
                repository.getMoviesList(Constants.API_KEY, 1)
            } catch (e: Exception) {
                null
            }

            response
                ?.onSuccess {
                    android.util.Log.d("Gajanand", "getMovieData:  success")
                    _movieListState.value = Resource.Success(it)
                }
                ?.onError {
                    android.util.Log.d("Gajanand", "getMovieData:  error")
                    _movieListState.value = Resource.Error("Something went wrong")
                }
        }
    }
}