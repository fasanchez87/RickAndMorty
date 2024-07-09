package com.me.network.api

import com.me.network.entity.CharacterEntity
import com.me.network.response.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {

    @GET(ENDPOINT)
    suspend fun getCharacters(
       @Query("page") page: Int = 1
    ): Response<CharactersResponse>

    @GET("$ENDPOINT/{id}")
    suspend fun getCharacterById(
        @Path("id") id: String
    ): Response<CharacterEntity>

    companion object {
        private const val ENDPOINT = "api/character"
    }
}