package com.me.rickmorty.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.ZonedDateTime

@JsonClass(generateAdapter = true)
data class CharacterEntity(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "status")
    val status: String,
    @Json(name = "species")
    val species: String,
    @Json(name = "type")
    val type: String,
    @Json(name="gender")
    val gender: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "created")
    val created: ZonedDateTime
)