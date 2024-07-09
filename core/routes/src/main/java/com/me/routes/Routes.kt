package com.me.routes

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import kotlinx.serialization.Serializable

//Implementing the routes of the app by secure way with serialization
@Serializable
object SplashRoute

@Serializable
object CharacterRoute

@Serializable
data class CharacterDetailRoute(
    val id: String
)








//
//sealed class Routes(
//    val route: String,
//    val navArguments: List<NamedNavArgument> = emptyList()
//) {
//
//    data object Splash : Routes(
//        "splash"
//    )
//
//    data object Characters : Routes(
//        "characters"
//    ){
//        fun createRoute() = "characters"
//    }
//
//    data object CharacterDetail : Routes(
//            "detailCharacter/{id}",
//            navArguments = listOf(navArgument("id") {
//                type = NavType.StringType
//            })
//    ) {
//        fun createRoute(idCharacter: String) = "detailCharacter/${idCharacter}"
//    }
//}