package com.me.ui.character

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.me.core.model.data.CharacterModel
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.me.designsystem.MeText
import com.me.designsystem.NiaTheme_toedit
import com.me.designsystem.fontMonserratLight
import com.me.designsystem.fontMonserratSemiBold
import com.me.ui.R

//@Composable
//fun CharacterList(
//    characters: List<CharacterModel>,
//    context: Context,
//    onClick: (CharacterModel) -> Unit
//) {
//    LazyColumn(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.spacedBy(8.dp),
//        contentPadding = PaddingValues(start = 8.dp, end = 8.dp, top = 10.dp)
//    ){
//        items(
//            items = characters,
//            //Add ever task id as a key to the list item for better performance and recomposition
//            key = { character ->
//                character.id
//            }
//        ) { character ->
//            CharacterItemView(
//                character = character,
//                context = context,
//                onClick = onClick
//            )
//        }
//    }
//}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterItemView(
    character: CharacterModel,
    context: Context,
    onClick: (CharacterModel) -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = {

                })
            }
            .clickable {
                onClick(character)
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
                    .align(Alignment.CenterVertically)
                    .padding(start = 20.dp),
            ) {
                Text(
                    text = character.name,
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.titleMedium,
                )
//                Text(
//                    text = character.name,
//                    fontSize = 16.sp,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(bottom = 3.dp),
//                    fontFamily = FontFamily(Font(R.font.montserrat_semibold))
//                )
//                MeText(
//                    text = character.name,
//                    fontSize = 16,
//                    modifier = Modifier,
//                    padding = 3.dp,
//                    isFillMaxWidth = true,
//                    fontFamily = fontMonserratSemiBold()
//                )

//                Text(
//                    text = character.species.id,
//                    fontSize = 16.sp,
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    fontFamily = FontFamily(Font(R.font.montserrat_light))
//                )

                Text(
                    text = character.species.id,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 4.dp,
                            bottom = 4.dp
                        ),
                    style = MaterialTheme.typography.titleSmall,
                )
//
//                MeText(
//                    text = character.species.id,
//                    fontSize = 16,
//                    modifier = Modifier,
//                    isFillMaxWidth = true,
//                    fontFamily = fontMonserratLight()
//                )

//                Text(
//                    text = character.status.id,
//                    fontSize = 16.sp,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(bottom = 5.dp),
//                    fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
//                    color = Color(character.status.color(context))
//                )

//                MeText(
//                    text = character.status.id,
//                    fontSize = 16,
//                    modifier = Modifier,
//                    padding = 5.dp,
//                    isFillMaxWidth = true,
//                    fontFamily = fontMonserratSemiBold()
//                )

                Text(
                    text = character.species.id,
                    modifier = Modifier
                        .fillMaxWidth(),
                    Color(character.status.color(context)),
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}

@Preview("Character Preview")
@Composable
private fun ItemCharacterPreview() {
    CompositionLocalProvider(
        LocalInspectionMode provides true,
    ) {
        NiaTheme_toedit {
            Surface {
                val currentContext = LocalContext.current
                CharacterItemView(
                    character = CharacterModel(
                        id = "1",
                        name = "Rick Morty",
                        status = CharacterModel.Status.ALIVE,
                        species = CharacterModel.Species.HUMAN,
                        type = "Mad Scientist",
                        gender = CharacterModel.Gender.MALE,
                        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                        url = "https://rickandmortyapi.com/api/character/1",
                        created = "2017-11-04T18:48:46.250Z"
                    ),
                    context = currentContext,
                    onClick = { }
                )
            }
        }
    }
}