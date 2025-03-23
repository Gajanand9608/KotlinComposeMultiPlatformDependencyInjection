package com.learning.helloworld.di

import com.learning.helloworld.dependency.KtorHttpClient
import com.learning.helloworld.dependency.MyRepository
import com.learning.helloworld.dependency.MyRepositoryImpl
import com.learning.helloworld.dependency.MyViewModel
import com.learning.helloworld.remote.MovieListApi
import com.learning.helloworld.repository.MovieListRepository
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule : Module

val sharedModule = module {
    single { KtorHttpClient().client }
    single { MovieListApi(get()) }
    single { MovieListRepository(get()) }
    single { MyViewModel(get()) }
}