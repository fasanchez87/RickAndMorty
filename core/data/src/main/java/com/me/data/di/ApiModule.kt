package com.me.data.di

import com.me.network.configuration.BaseRequest
import com.me.network.configuration.ConfigurationRestClient
import com.me.network.configuration.ConfigurationRestClientImpl
import com.me.utils.exception.ExceptionMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Singleton
//
//@InstallIn(ActivityRetainedComponent::class)
//@Module
//class ApiModule {
//
////    @Provides
////    @Singleton
////    fun provideConfigurationRestClient(exceptionMapper: ExceptionMapper): ConfigurationRestClient {
////        return ConfigurationRestClientImpl(exceptionMapper)
////    }
////
////    @Provides
////    fun provideCharacterApi(baseRequest: BaseRequest): CharacterApi {
////        return baseRequest.retrofit.create(CharacterApi::class.java)
////    }
//}
