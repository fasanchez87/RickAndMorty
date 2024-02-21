package com.me.rickmorty.app.di

import android.content.Context
import com.google.gson.Gson
import com.me.rickmorty.Navigator
import com.me.rickmorty.app.App
import com.squareup.moshi.Moshi
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class Module {

    @Provides
    @Singleton
    fun provideNavigator(@ApplicationContext application: Context): Navigator {
        return Navigator(application)
    }

//    @Provides
//    @Singleton
//    fun provideConverterFactory(gson: Gson): Converter.Factory {
//        return GsonConverterFactory.create(gson)
//    }

//    @Provides
//    @Singleton
//    fun provideGson(): Gson {
//        return App.gsonSingleton.create()
//    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return App.moshiSingleton.build()
    }
}
//
//
//val appModule = module {
//
//    single {
//        Navigator(get())
//    }
//
//    single<Converter.Factory> {
//        val gson = get<Gson>()
//        GsonConverterFactory.create(gson)
//    }
//
//    single { App.gsonSingleton.create() }
//
//    single { App.moshiSingleton.build() }
//}
