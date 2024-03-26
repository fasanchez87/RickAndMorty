package com.me.rickmorty.data.repository.network

import com.me.rickmorty.app.ui.base.BaseRequest
import com.me.rickmorty.data.mapper.CharacterMapper
import com.me.rickmorty.data.repository.network.api.CharacterApi
import com.me.rickmorty.domain.model.CharacterModel
import com.me.rickmorty.domain.repository.CharacterRepository
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
}
