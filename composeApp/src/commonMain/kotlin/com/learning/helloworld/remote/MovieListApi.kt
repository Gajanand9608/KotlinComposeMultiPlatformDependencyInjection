package com.learning.helloworld.remote

import com.learning.helloworld.common.Constants
import com.learning.helloworld.util.NetworkError
import com.learning.helloworld.util.Result
import com.learning.helloworld.remote.dto.MoviesListResponse
import com.learning.helloworld.util.Error
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException

class MovieListApi(private val client: HttpClient) {
    suspend fun getMoviesList(apiKey: String, page: Int): Result<MoviesListResponse, NetworkError> {
        val response = try {
            client.get(Constants.BASE_URL) {
                parameter("api_key", apiKey)
                parameter("page", page)
                accept(ContentType.Application.Json)
            }
        } catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }
        android.util.Log.d("Gajanand", "getMoviesList: response " + response.status)

        return when(response.status.value) {
            in 200..299 -> {
                try {
                    val responseBody = response.body<MoviesListResponse>()
                    Result.Success(responseBody)
                }catch (e: Exception){
                    Result.Error(NetworkError.UNKNOWN)
                }
            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }
}