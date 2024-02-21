package com.me.rickmorty.domain.model

import android.os.Parcelable
import com.me.rickmorty.util.ItemViewable
import com.me.rickmorty.R
import com.me.rickmorty.util.parseFullDate
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.time.ZonedDateTime

@Parcelize
@JsonClass(generateAdapter = true)
data class CharacterModel(
    override val id: String,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val url: String,
    @Json(name = "created")
    val created: String
) : ItemViewable, Parcelable {

    override fun getLayout(): Int = R.layout.item_character

    companion object {
        fun getDateText(data: ZonedDateTime): String =
            parseFullDate(data).orEmpty()
    }
}
