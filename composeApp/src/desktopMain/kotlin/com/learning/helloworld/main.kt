package com.learning.helloworld

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.learning.helloworld.di.initKoin
import com.learning.helloworld.networking.InsertCensorClient
import com.learning.helloworld.networking.createHttpClient
import io.ktor.client.engine.okhttp.OkHttp

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "KotlinProjectHelloWorld",
        ) {
            App()
        }
    }
}