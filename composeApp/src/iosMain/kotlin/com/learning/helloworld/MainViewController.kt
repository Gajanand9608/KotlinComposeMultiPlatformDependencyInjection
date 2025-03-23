package com.learning.helloworld

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.learning.helloworld.di.initKoin
import com.learning.helloworld.networking.InsertCensorClient
import com.learning.helloworld.networking.createHttpClient
import io.ktor.client.engine.darwin.Darwin

fun MainViewController() = ComposeUIViewController(configure = {
    initKoin()
}) {
    App()
}