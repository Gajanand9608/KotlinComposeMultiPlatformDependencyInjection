package com.learning.helloworld

import android.app.Application
import com.learning.helloworld.di.initKoin
import com.learning.helloworld.di.sharedModule
import org.koin.android.ext.koin.androidContext

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MyApplication)
        }
    }
}