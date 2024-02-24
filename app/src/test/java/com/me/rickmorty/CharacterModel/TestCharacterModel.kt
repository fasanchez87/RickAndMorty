package com.me.rickmorty.CharacterModel
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.me.rickmorty.R
import com.me.rickmorty.domain.model.CharacterModel
import com.me.rickmorty.domain.model.CharacterModel.Companion.getDateText
import com.me.rickmorty.util.extensions.asResourceColor
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.time.ZonedDateTime

//Unit testing
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [33])
class TestCharacterModel {

    private lateinit var characterModel: CharacterModel

    private lateinit var context: Context

    @Before
    fun setUpCharacterModel() {

        context = ApplicationProvider.getApplicationContext()

        characterModel = CharacterModel(
            id = "1",
            name = "Rick",
            status = CharacterModel.Status.ALIVE,
            species = CharacterModel.Species.ALIEN,
            type = "Test",
            gender = CharacterModel.Gender.FEMALE,
            url = "https://rickandmortyapi.com/api/character/1",
            created = ZonedDateTime.now().toString(),
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        )
    }

    @Test
    fun givenDate_whenFormatDate_thenGetFormattedDate() {
        val zonedDateTime = ZonedDateTime.parse("2017-11-04T18:50:21.651Z")
        val dateText = getDateText(zonedDateTime)
        assertEquals("04/11/2017 13:50", dateText)
    }

    @Test
    fun givenCharacterModel_whenGetStatus_thenGetStatusAlive() {
        val status = characterModel.status
        assertEquals(CharacterModel.Status.ALIVE, status)
    }

    @Test
    fun givenCharacterModel_whenGetSpecies_thenGetSpeciesAlien() {
        val species = characterModel.species
        assertEquals(CharacterModel.Species.ALIEN, species)
    }

    @Test
    fun givenCharacterModel_whenGetGender_thenGetGenderFemale() {
        val gender = characterModel.gender
        assertEquals(CharacterModel.Gender.FEMALE, gender)
    }

    @Test
    fun givenCharacterModel_whenGetStatusAlive_thenReturnColorGreen_1(){
        val status = characterModel.status.color(context)
        assertEquals(R.color.color3.asResourceColor(context), status)
    }
}