package com.learning.helloworld.networking

import kotlinx.serialization.Serializable

@Serializable
data class CensoredText(
    val result: String
)