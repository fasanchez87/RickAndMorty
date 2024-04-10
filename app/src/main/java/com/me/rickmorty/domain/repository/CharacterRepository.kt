package com.me.rickmorty.domain.repository

import com.me.rickmorty.domain.model.CharacterModel

interface CharacterRepository {

    suspend fun getListCharacter(): List<CharacterModel>

    suspend fun getCharacterById(id: String): CharacterModel
}
