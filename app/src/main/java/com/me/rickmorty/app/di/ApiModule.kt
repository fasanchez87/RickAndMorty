package com.me.rickmorty.app.di

import com.me.rickmorty.util.BaseRequest
import com.me.rickmorty.util.ConfigurationRestClient
import com.me.rickmorty.util.ConfigurationRestClientImpl
import com.me.rickmorty.util.ExceptionMapper
import com.me.rickmorty.data.repository.network.api.CharacterApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Singleton

@InstallIn(ActivityRetainedComponent::class)
@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideConfigurationRestClient(exceptionMapper: ExceptionMapper): ConfigurationRestClient {
        return ConfigurationRestClientImpl(exceptionMapper)
    }

    @Provides
    fun provideCharacterApi(baseRequest: BaseRequest): CharacterApi {
        return baseRequest.retrofit.create(CharacterApi::class.java)
    }
}
