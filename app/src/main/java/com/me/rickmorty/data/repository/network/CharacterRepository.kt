package com.me.rickmorty.data.repository.network

import com.me.rickmorty.BaseRequest
import com.me.rickmorty.data.mapper.CharacterMapper
import com.me.rickmorty.data.repository.network.api.CharacterApi
import com.me.rickmorty.domain.model.CharacterModel
import javax.inject.Inject


interface CharacterRepository {
    suspend fun getListCharacter(): List<CharacterModel>
}
//
//class CharacterRepository @Inject constructor(
//    private val baseRequest: BaseRequest,
//    private val characterApi: CharacterApi,
//    private val characterMapper: CharacterMapper
//) {
//    suspend fun getListCharacter() =
//        baseRequest.request {
//            characterApi.getCharacters()
//        }.let {
//            characterMapper.toListModel(it.characters.toMutableList())
//        }
//}