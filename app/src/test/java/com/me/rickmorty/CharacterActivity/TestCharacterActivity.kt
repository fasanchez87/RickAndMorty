package com.me.rickmorty.CharacterActivity
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.google.common.truth.Truth.assertThat
import com.me.rickmorty.R
import com.me.rickmorty.app.ui.character.CharacterViewModel
import com.me.rickmorty.app.ui.character.CharactersActivity
import com.me.rickmorty.app.ui.splash.SplashActivity
import com.me.rickmorty.domain.model.CharacterModel
import com.me.rickmorty.domain.repository.CharacterRepository
import com.me.rickmorty.util.extensions.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

//Integration testing
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [33])
class CharactersActivityTest {

    @Test
    fun giveSplashActivity_whenLaunchSplashActivity_thenShowCharacterActivity() {

        Robolectric.buildActivity(SplashActivity::class.java)
            .create()
            .start()
            .resume()
            .visible()

        val charactersActivity = Robolectric.buildActivity(CharactersActivity::class.java)
            .create()
            .start()
            .resume()
            .visible()

        assertThat(charactersActivity.get().lifecycle.currentState).isEqualTo(Lifecycle.State.RESUMED)

        val recyclerView = charactersActivity.get().findViewById<RecyclerView>(R.id.rv_characters)
        assertThat(recyclerView).isNotNull()
        assertThat(recyclerView.visibility).isEqualTo(View.VISIBLE)
    }

    @Test
    fun giveCharacterRepository_whenCallRepositoryFromViewmodel_thenGettingExpectedValues() {

        val mockCharacterRepository = mockk<CharacterRepository>()

        val mockData = listOf(
            CharacterModel(
                "1",
                "Rick",
                CharacterModel.Status.ALIVE,
                CharacterModel.Species.ALIEN,
                "Earth",
                CharacterModel.Gender.FEMALE,
                "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
            ),
            CharacterModel(
                "2",
                "Martin",
                CharacterModel.Status.ALIVE,
                CharacterModel.Species.ALIEN,
                "Earth",
                CharacterModel.Gender.FEMALE,
                "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
            )
        )

        coEvery { mockCharacterRepository.getListCharacter() } returns mockData

        val viewModel = CharacterViewModel(mockCharacterRepository)

        //getOrAwaitValue is an extension function that we will create test on Observer
        val value = viewModel.getCharacters().getOrAwaitValue().getValueOrError()
        assertNotNull(value)
    }
}

