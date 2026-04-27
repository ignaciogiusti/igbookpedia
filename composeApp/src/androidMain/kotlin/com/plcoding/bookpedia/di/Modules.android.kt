package com.plcoding.bookpedia.di

import com.plcoding.bookpedia.book.data.database.DbFactory
import io.ktor.client.engine.HttpClientEngine
import okhttp3.OkHttp
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { OkHttp.create() }
        single { DbFactory(androidApplication()) }
    }