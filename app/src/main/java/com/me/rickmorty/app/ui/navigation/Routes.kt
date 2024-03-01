package com.me.rickmorty.app.ui.navigation

sealed class Routes(
    val route: String
) {
    object CharactersActivity : Routes("CharactersActivity")
    object DetailCharacterActivity : Routes("DetailCharacterActivity/{character}")
}