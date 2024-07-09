package com.me.rickmorty.data.repository.network.api

import com.me.rickmorty.data.entity.CharacterEntity
import com.me.rickmorty.data.response.CharacterResponse
//import com.me.rickmorty.data.response.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {

//    @GET(ENDPOINT)
//    suspend fun getCharacters(
//       @Query("page") page: Int = 1
//    ): Response<CharactersResponse>

    @GET("$ENDPOINT/{id}")
    suspend fun getCharacterById(
        @Path("id") id: String
    ): Response<CharacterEntity>

    companion object {
        private const val ENDPOINT = "api/character"
    }
}