package com.learning.helloworld.dependency

import android.content.Context

actual class DbClient(
    private val context: Context
)