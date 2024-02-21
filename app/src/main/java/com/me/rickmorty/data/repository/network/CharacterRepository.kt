package com.me.rickmorty.data.repository.network

import com.me.rickmorty.domain.model.CharacterModel


interface CharacterRepository {
    suspend fun getListCharacter(): List<CharacterModel>
}
