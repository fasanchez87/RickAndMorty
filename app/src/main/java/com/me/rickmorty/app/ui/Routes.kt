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
            "detailCharacter/{id}",
            navArguments = listOf(navArgument("id") {
                type = NavType.StringType
            })
    ) {
        fun createRoute(idCharacter: String) = "detailCharacter/${idCharacter}"
    }
}