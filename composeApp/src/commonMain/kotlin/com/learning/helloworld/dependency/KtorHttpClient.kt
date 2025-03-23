package com.learning.helloworld.dependency

import io.ktor.client.HttpClient

expect class KtorHttpClient() {
    val client: HttpClient
}