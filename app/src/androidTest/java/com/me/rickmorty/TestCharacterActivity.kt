package com.me.rickmorty

import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.me.repository.CharacterRepositoryTest
import com.me.rickmorty.app.di.RepositoryModule
import com.me.rickmorty.app.ui.character.CharactersActivity
import com.me.rickmorty.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//E2E Testing
@HiltAndroidTest
@UninstallModules(RepositoryModule::class)
@RunWith(AndroidJUnit4::class)
@MediumTest
class TestCharacterActivity {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Module
    @InstallIn(ActivityRetainedComponent::class)
    object RepositoryModuleTest {
        @Provides
        fun providesCharacterRepository(): CharacterRepository {
            return CharacterRepositoryTest()
        }
    }

    @Before
    fun initRepository() {
        hiltRule.inject()
    }

    @Test
    fun clickOnRecyclerViewItem_opensDetailActivity() {
        launchActivity<CharactersActivity>()
        Thread.sleep(2000)
    }
}