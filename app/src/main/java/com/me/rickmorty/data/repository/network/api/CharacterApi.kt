package com.me.rickmorty.data.repository.network.api

import com.me.rickmorty.data.response.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET

interface CharacterApi {

    @GET(ENDPOINT)
    suspend fun getCharacters(): Response<CharactersResponse>

    companion object {
        private const val ENDPOINT = "api/character"
    }
}