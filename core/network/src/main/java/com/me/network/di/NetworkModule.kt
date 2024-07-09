package com.me.network.di

import com.me.network.configuration.BaseRequest
import com.me.network.configuration.ConfigurationRestClient
import com.me.network.configuration.ConfigurationRestClientImpl
import com.me.network.factory.BaseRequestFactory
import com.me.network.factory.RetrofitFactory
import com.me.network.interceptor.ResponseInterceptor
import com.me.utils.exception.ExceptionMapper
import com.me.utils.time.ZonedDateTimeAdapter
import com.me.utils.wrapper.WrapperResponseMapper
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

    @Provides
    @Singleton
    fun provideMoshiBuilder(): Moshi.Builder {
        return Moshi.Builder()
    }

    @Provides
    @Singleton
    fun provideMoshi(moshiBuilder: Moshi.Builder): Moshi {
        moshiBuilder.add(ZonedDateTimeAdapter())
        return moshiBuilder.build()
    }
}