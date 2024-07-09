package com.me.characters

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.me.core.model.data.CharacterModel
import com.me.ui.character.CharacterItemView
import com.me.utils.extensions.ObserveAsFlowEnhanced
import timber.log.Timber

@Composable
fun CharacterScreen(
    onClick: (CharacterModel) -> Unit,
    viewModel: CharacterViewModel = hiltViewModel(),
    titleAppBar: (String) -> Unit,
    showAppBar: (Boolean) -> Unit,
    context: (Context) -> Unit,
    isLoading: (Boolean) -> Unit,
    ) {

    val currentContext = LocalContext.current.apply {
        context(this)
    }

    val isLoadingRemember = rememberSaveable {
        mutableStateOf(false)
    }

    titleAppBar("Characters")

    showAppBar(true)

//    LaunchedEffect(key1 = true) {
//        viewModel.getCharacters()
//    }

    ObserveAsFlowEnhanced(
        stateFlow = viewModel.characters,
        onSuccess = {
            CharacterList(
                it,
                currentContext,
                onClick
            )
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
        }
    )

    LaunchedEffect(isLoadingRemember.value) {
        isLoading(isLoadingRemember.value)
    }
}

@Composable
fun CharacterList(
    characters: List<CharacterModel>,
    context: Context,
    onClick: (CharacterModel) -> Unit
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
                context = context,
                onClick = onClick
            )
        }
    }
}
//
//@OptIn(ExperimentalGlideComposeApi::class)
//// @Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun CharacterItemView(
//    character: CharacterModel,
//    context: Context,
//    onClick: (CharacterModel) -> Unit
//){
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//            .pointerInput(Unit) {
//                detectTapGestures(
//                    onLongPress = {
//
//                    }
//                )
//            }
//            .clickable {
//                onClick(character)
//            },
//        colors = CardDefaults
//            .cardColors(
//                containerColor = Color.White
//            ),
//        elevation = CardDefaults.cardElevation(8.dp)
//    ) {
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//
//            GlideImage(
//                model = character.image,
//                contentDescription = "Character Image",
//                modifier = Modifier
//                    .clip(CircleShape)
//                    .size(80.dp)
//                    .align(Alignment.CenterVertically),
//                loading = placeholder(R.drawable.icn_close)
//            )
//
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .align(Alignment.Top)
//                    .padding(start = 20.dp),
//            ) {
//                Text(
//                    text = character.name,
//                    fontSize = 16.sp,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(bottom = 3.dp),
//                    fontFamily = FontFamily(Font(R.font.montserrat_semibold))
//                )
//
//                Text(
//                    text = character.species.id,
//                    fontSize = 16.sp,
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    fontFamily = FontFamily(Font(R.font.montserrat_light))
//                )
//
//                Text(
//                    text = character.status.id,
//                    fontSize = 16.sp,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(bottom = 5.dp),
//                    fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
//                    color = Color(character.status.color(context))
//                )
//            }
//        }
//    }
//}