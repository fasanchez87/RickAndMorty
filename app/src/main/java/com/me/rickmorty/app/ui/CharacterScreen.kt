package com.me.rickmorty.app.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.me.rickmorty.R
import com.me.rickmorty.app.ui.character.CharacterViewModel
import com.me.rickmorty.domain.model.CharacterModel
import com.me.rickmorty.util.extensions.ObserveStateFlow
import timber.log.Timber

@Composable
fun CharacterScreen(
    onClick: (CharacterModel) -> Unit,
    viewModel: CharacterViewModel = hiltViewModel(),
    isLoading: (Boolean) -> Unit,
    titleAppBar: (String) -> Unit,
    showAppBar: (Boolean) -> Unit,
    context: (Context) -> Unit
) {

    val currentContext = LocalContext.current.apply {
        context(this)
    }

    val isLoadingRemember = remember {
        mutableStateOf(false)
    }

    titleAppBar("Characters")

    showAppBar(true)

    ObserveStateFlow(
        stateFlow = viewModel.getCharacters(),
        onSuccess = {
            CharacterList(
                it,
                currentContext
            )
            isLoadingRemember.value = false
        },
        onError = { t ->
            //Handle error
            t.message
            isLoadingRemember.value = false
        },
        onEmpty = {
            //Handle empty
            Timber.tag("CharactersActivity").i("Empty")
            isLoadingRemember.value = false

        },
        onLoading = {
            //Handle loading
            isLoadingRemember.value = true
            Timber.tag("CharactersActivity").i("Loading")
        },
        context = LocalContext.current,
        lifecycleOwner =  LocalLifecycleOwner.current
    )

    LaunchedEffect(isLoadingRemember.value) {
        isLoading(isLoadingRemember.value)
    }
}

@Composable
fun CharacterList(
    characters: List<CharacterModel>,
    context: Context
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(start = 8.dp, end = 8.dp, top = 10.dp)
    ){
        items(
            items = characters,
            //Add ever task id as a key to the list item for better performance and recomposition
            key = { character ->
                character.id
            }
        ) { character ->
            CharacterItemView(
                character = character,
                context = context
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
// @Preview(showBackground = true, showSystemUi = true)
@Composable
fun CharacterItemView(
    character: CharacterModel,
    context: Context
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {

                    }
                )
            }
            .clickable {
                Toast
                    .makeText(context, "click", Toast.LENGTH_LONG)
                    .show()
            },
        colors = CardDefaults
            .cardColors(
                containerColor = Color.White
            ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            GlideImage(
                model = character.image,
                contentDescription = "Character Image",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(80.dp)
                    .align(Alignment.CenterVertically),
                loading = placeholder(R.drawable.icn_close)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Top)
                    .padding(start = 20.dp),
            ) {
                Text(
                    text = character.name,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 3.dp),
                    fontFamily = FontFamily(Font(R.font.montserrat_semibold))
                )

                Text(
                    text = character.species.id,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth(),
                    fontFamily = FontFamily(Font(R.font.montserrat_light))
                )

                Text(
                    text = character.status.id,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp),
                    fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                    color = Color(character.status.color(context))
                )
            }
        }
    }
}