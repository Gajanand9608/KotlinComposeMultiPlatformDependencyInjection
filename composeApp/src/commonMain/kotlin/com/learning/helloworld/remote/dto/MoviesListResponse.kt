package com.learning.helloworld.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class MoviesListResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)