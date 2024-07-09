package com.me.rickmorty.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.me.core.model.data.CharacterModel
import com.me.rickmorty.R
import com.me.rickmorty.app.ui.character.CharacterDetailViewModel
import com.me.rickmorty.util.extensions.ObserveAsFlowEnhanced
import timber.log.Timber

@Composable
fun CharacterDetailScreen(
    idCharacter: String,
    titleAppBar: (String) -> Unit,
    isLoading: (Boolean) -> Unit,
    onBack: () -> Unit,
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {

    titleAppBar("Character Detail")

    val isLoadingRemember = rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit){
        viewModel.getCharacter(idCharacter)
    }

    LaunchedEffect(isLoadingRemember.value) {
        isLoading(isLoadingRemember.value)
    }

    ObserveAsFlowEnhanced(
        stateFlow = viewModel.character,
        onSuccess = {
            DrawDetailCharacter(it)
            isLoadingRemember.value = false
        },
        onError = {
            //Handle error
            Timber.tag("CharactersActivity").e(it)
            isLoadingRemember.value = false
        },
        onEmpty = {
            //Handle empty
            Timber.tag("CharactersActivity").i("Empty")
            isLoadingRemember.value = false
        },
        onLoading = {
            //Handle loading
            Timber.tag("CharactersActivity").i("Loading")
            isLoadingRemember.value = true
        })
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DrawDetailCharacter(character: CharacterModel) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        GlideImage(
            model = character.image,
            contentDescription = "Character Image",
            loading = placeholder(R.drawable.icn_close),
            modifier = Modifier.fillMaxWidth()
                .height(400.dp),
            contentScale = ContentScale.FillWidth
        )

        Text(
            fontSize = 20.sp,
            text = character.name,
            fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
            color = androidx.compose.ui.graphics.Color.Black,
            modifier = Modifier
                .padding(top = 8.dp, start = 16.dp, end = 16.dp),
        )
    }
}