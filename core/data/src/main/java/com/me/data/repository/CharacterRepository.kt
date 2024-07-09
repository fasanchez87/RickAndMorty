package com.me.data.repository

import com.me.core.model.data.CharacterModel

interface CharacterRepository {

    suspend fun getListCharacter(): List<CharacterModel>

    suspend fun getCharacterById(id: String): CharacterModel
}
