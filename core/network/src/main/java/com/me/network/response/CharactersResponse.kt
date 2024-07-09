package com.me.network.response

import com.me.network.entity.CharacterEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharactersResponse(
    @Json(name = "results")
    val characters: List<CharacterEntity>
)