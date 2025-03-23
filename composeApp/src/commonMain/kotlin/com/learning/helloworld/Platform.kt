package com.learning.helloworld

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform