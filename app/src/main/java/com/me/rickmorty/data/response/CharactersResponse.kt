package com.me.rickmorty.data.response

import com.me.rickmorty.data.entity.CharacterEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharactersResponse(
    @Json(name = "results")
    val characters: List<CharacterEntity>
)