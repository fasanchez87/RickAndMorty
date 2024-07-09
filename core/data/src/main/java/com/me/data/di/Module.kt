package com.me.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class Module {

//    @Provides
//    @Singleton
//    fun provideNavigator(@ApplicationContext application: Context): Navigator {
//        return Navigator(application)
//    }

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

//    @Provides
//    @Singleton
//    fun provideMoshi(): Moshi {
//        return App.moshiSingleton.build()
//    }
}

