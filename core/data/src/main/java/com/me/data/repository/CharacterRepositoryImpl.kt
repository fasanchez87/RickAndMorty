package com.me.data.repository

import com.me.core.model.data.CharacterModel
import com.me.data.mapper.CharacterMapper
import com.me.network.api.CharacterApi
import com.me.network.configuration.BaseRequest
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val baseRequest: BaseRequest,
    private val characterApi: CharacterApi,
    private val characterMapper: CharacterMapper
) : CharacterRepository {

    override suspend fun getListCharacter(): List<CharacterModel> =
        baseRequest.request {
            characterApi.getCharacters()
        }.let {
            characterMapper.toListModel(it.characters.toMutableList())
        }

    override suspend fun getCharacterById(id: String): CharacterModel =
        baseRequest.request {
            characterApi.getCharacterById(id)
        }.let {
            characterMapper.toModel(it)
        }
}
