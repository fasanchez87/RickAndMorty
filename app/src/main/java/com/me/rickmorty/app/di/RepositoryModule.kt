package com.me.rickmorty.app.di

import com.me.rickmorty.app.ui.base.BaseRequest
import com.me.rickmorty.data.mapper.CharacterMapper
import com.me.rickmorty.domain.repository.CharacterRepository
import com.me.rickmorty.data.repository.network.api.CharacterApi
import com.me.rickmorty.data.repository.network.CharacterRepositoryImpl
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

