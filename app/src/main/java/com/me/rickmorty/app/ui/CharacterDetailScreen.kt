package com.me.rickmorty.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.me.rickmorty.R
import com.me.rickmorty.domain.model.CharacterModel

@Composable
fun CharacterDetailScreen(
    characterModel: String,
    titleAppBar: (String) -> Unit,
    onBackPressed: () -> Unit
) {

    titleAppBar(characterModel)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            fontSize = 20.sp,
            modifier = Modifier.fillMaxSize(),
            text = characterModel,
            fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
            color = androidx.compose.ui.graphics.Color.Black
        )
    }

}