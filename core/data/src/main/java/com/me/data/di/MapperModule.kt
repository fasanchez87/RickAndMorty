package com.me.data.di

import com.me.data.mapper.CharacterMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
class MapperModule {

    @Provides
    fun provideCharacterMapper(): CharacterMapper {
        return CharacterMapper()
    }
}
