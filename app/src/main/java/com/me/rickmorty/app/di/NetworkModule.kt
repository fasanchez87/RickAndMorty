package com.me.rickmorty.app.di

import com.me.rickmorty.BaseRequest
import com.me.rickmorty.ConfigurationRestClient
import com.me.rickmorty.ConfigurationRestClientImpl
import com.me.rickmorty.ExceptionMapper
import com.me.rickmorty.WrapperResponseMapper
import com.me.rickmorty.data.repository.network.factory.BaseRequestFactory
import com.me.rickmorty.data.repository.network.factory.RetrofitFactory
import com.me.rickmorty.data.repository.network.interceptor.ResponseInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideConfigurationRestClient(exceptionMapper: ExceptionMapper): ConfigurationRestClient {
        return ConfigurationRestClientImpl(exceptionMapper)
    }

    @Provides
    @Singleton
    fun provideBaseRequest(
        configuration: ConfigurationRestClient,
        retrofit: Retrofit
    ): BaseRequest {
        return BaseRequestFactory.getFactory(retrofit, configuration).getBaseRequest()
    }

//    @Provides
//    @Singleton
//    fun provideMoshi(): Moshi {
//        return Moshi.Builder().build()
//    }

    @Provides
    @Singleton
    fun provideConverterFactory(moshi: Moshi): Converter.Factory {
        return MoshiConverterFactory.create(moshi).withNullSerialization()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        configuration: ConfigurationRestClient,
        responseInterceptor: ResponseInterceptor,
        converterFactory: Converter.Factory
    ): Retrofit {
        return RetrofitFactory.getFactory(
            configuration,
            responseInterceptor,
            converterFactory
        ).getRetrofit()
    }

    @Provides
    @Singleton
    fun provideWrapperResponseMapper(): WrapperResponseMapper {
        return WrapperResponseMapper()
    }

    @Provides
    @Singleton
    fun provideExceptionMapper(wrapperResponseMapper: WrapperResponseMapper): ExceptionMapper {
        return ExceptionMapper(wrapperResponseMapper)
    }

    @Provides
    @Singleton
    fun provideResponseInterceptor(configuration: ConfigurationRestClient): ResponseInterceptor {
        return ResponseInterceptor(configuration)
    }
}