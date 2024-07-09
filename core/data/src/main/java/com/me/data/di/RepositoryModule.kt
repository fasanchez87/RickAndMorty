package com.me.data.di

import com.me.data.mapper.CharacterMapper
import com.me.data.repository.CharacterRepository
import com.me.data.repository.CharacterRepositoryImpl
import com.me.network.api.CharacterApi
import com.me.network.configuration.BaseRequest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
class RepositoryModule {

    @Provides
    fun provideCharacterRepository(
        baseRequest: BaseRequest,
        characterApi: CharacterApi,
        characterMapper: CharacterMapper
    ): CharacterRepository {
        return CharacterRepositoryImpl(
            baseRequest,
            characterApi,
            characterMapper
        )
    }
}

