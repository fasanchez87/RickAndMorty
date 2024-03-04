package com.me.rickmorty.app.ui

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.me.rickmorty.domain.model.CharacterModel

sealed class Routes(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {

    object Splash : Routes(
        "splash"
    )

    object Characters : Routes(
        "characters"
    ){
        fun createRoute() = "characters"
    }

    object CharacterDetail : Routes(
            "detailCharacter/{character}",
            navArguments = listOf(navArgument("character") {
                type = NavType.StringType
            })
    ) {
        fun createRoute(character: CharacterModel) = "detailCharacter/${character}"
    }
}