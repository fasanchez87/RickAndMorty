package com.me.rickmorty.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.me.rickmorty.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    showAppBar: (Boolean) -> Unit = {}
) {

    showAppBar(false)

    LaunchedEffect(true) {
        delay(2000)
        navController.navigate(Routes.Characters.route) {
            popUpTo(Routes.Splash.route) { inclusive = true }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_splash),
            contentDescription = "Logo Splash",
            modifier = Modifier.fillMaxSize().padding(70.dp)
        )
    }
}
