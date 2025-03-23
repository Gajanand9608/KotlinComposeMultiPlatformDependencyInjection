package com.learning.helloworld.di

import com.learning.helloworld.dependency.DbClient
import com.learning.helloworld.dependency.MyViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule = module {
    singleOf(::DbClient)
}