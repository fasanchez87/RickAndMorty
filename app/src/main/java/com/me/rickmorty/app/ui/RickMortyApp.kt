package com.me.rickmorty.app.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

//@Composable
//fun RickMortyApp() {
//    val navController = rememberNavController()
//    RickMortyNavHost(
//        navController = navController
//    )
//}

//@Composable
//fun RickMortyNavHost(
//    navController: NavHostController
//) {
//
//    val activity = (LocalContext.current as Activity)
//    NavHost(navController = navController, startDestination = Routes.Splash.route) {
//        composable(route = Routes.Splash.route) {
//            CharacterScreen(
//                onClick = {
//                    navController.navigate(
//                        Routes.Characters.createRoute()
//                    )
//                },
//                isLoading = {
//
//                }
//            )
//        }
//        composable(
//            route = Screen.PlantDetail.route,
//            arguments = Screen.PlantDetail.navArguments
//        ) {
//            PlantDetailsScreen(
//                onBackClick = { navController.navigateUp() },
//                onShareClick = {
//                    createShareIntent(activity, it)
//                },
//                onGalleryClick = {
//                    navController.navigate(
//                        Screen.Gallery.createRoute(
//                            plantName = it.name
//                        )
//                    )
//                }
//            )
//        }
//        composable(
//            route = Screen.Gallery.route,
//            arguments = Screen.Gallery.navArguments
//        ) {
//            GalleryScreen(
//                onPhotoClick = {
//                    val uri = Uri.parse(it.user.attributionUrl)
//                    val intent = Intent(Intent.ACTION_VIEW, uri)
//                    activity.startActivity(intent)
//                },
//                onUpClick = {
//                    navController.navigateUp()
//                })
//        }
//    }
//}