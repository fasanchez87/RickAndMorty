package com.me.rickmorty.domain.model

import android.content.Context
import android.os.Parcelable
import androidx.annotation.ColorRes
import com.me.rickmorty.util.tools.ItemViewable
import com.me.rickmorty.R
import com.me.rickmorty.util.extensions.asResourceColor
import com.me.rickmorty.util.extensions.parseFullDate
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.time.ZonedDateTime

@Parcelize
@JsonClass(generateAdapter = true)
data class CharacterModel(
    override val id: String,
    val name: String,
    val status: Status,
    val species: Species,
    val type: String,
    val gender: Gender,
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

    enum class Gender(
        val id: String
    ) {
        MALE(
            "Male"
        ),
        FEMALE(
            "Female"
        ),
        UNKNOWN(
            "unknown"
        );

        companion object {
            fun findById(id: String) = values().find { it.id == id }
                ?: throw IllegalArgumentException("Gender not found -> $id")
        }
    }

    enum class Status(
        val id: String,
        @ColorRes val color: (Context) -> Int
    ) {
        ALIVE(
            "Alive",
            { context ->
                R.color.color3.asResourceColor(context)
            }
        ),
        DEAD(
            "Dead",
            { context ->
                R.color.color4.asResourceColor(context)
            }
        ),
        UNKNOWN(
            "unknown",
            { context ->
                R.color.color8.asResourceColor(context)
            });

        companion object {
            fun findById(id: String) = values().find { it.id == id }
                ?: throw IllegalArgumentException("Status not found -> $id")
        }
    }

    enum class Species(
        val id: String
    ) {
        HUMAN("Human"),
        ALIEN("Alien"),
        HUMANOID("Humanoid"),
        MYTHOLOG("Mytholog"),
        ANIMAL("Animal"),
        ROBOT("Robot"),
        UNKNOWN("Unknown");

        companion object {
            fun findById(id: String) = Species.values().find { it.id == id } ?: throw IllegalArgumentException("Specie not found -> $id")
        }
    }



}
