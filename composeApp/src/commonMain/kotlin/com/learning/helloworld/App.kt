package com.learning.helloworld

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.learning.helloworld.common.Constants
import com.learning.helloworld.common.Resource
import com.learning.helloworld.dependency.MyViewModel
import com.learning.helloworld.remote.dto.MoviesListResponse
import com.learning.helloworld.remote.dto.Result
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinContext {
            NavHost(navController = rememberNavController(), startDestination = "home"){
                composable("home"){
                    val viewModel = koinViewModel<MyViewModel>()
                    val movieListValue = viewModel.movieListState.value
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        when(movieListValue){
                            is Resource.Error<MoviesListResponse> -> {
                                Text(text = movieListValue.message.toString())
                            }
                            is Resource.Loading<MoviesListResponse> -> {
                                CircularProgressIndicator()
                            }
                            is Resource.Success<MoviesListResponse> -> {
                                LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(10.dp)) {
                                    if (movieListValue.data?.results.isNullOrEmpty().not()) {
                                        items(movieListValue.data.results) { movie ->
                                            MovieCard(
                                                movie
                                            )
                                            Spacer(modifier = Modifier.height(16.dp))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                composable("detail"){
                    Box(modifier = Modifier.fillMaxSize()){
                        Text("detail")
                    }
                }
            }
        }
    }
}

@Composable
fun MovieCard(movie: Result) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .wrapContentHeight()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imageUrl = Constants.IMAGE_BASE_URL + movie.poster_path
//            Image(
//                model = imageResource(Res.drawable.ve),
//                contentDescription = "Movie Poster",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(200.dp)
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = movie.title,
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(4.dp)
            )

            Text(
                text = "Rating : ${movie.vote_average}",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal),
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}