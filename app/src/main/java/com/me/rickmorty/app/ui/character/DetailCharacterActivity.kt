package com.me.rickmorty.app.ui.character

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.me.rickmorty.R
import com.me.rickmorty.domain.model.CharacterModel

class DetailCharacterActivity : ComponentActivity() {

    private val character: CharacterModel by lazy {
        intent?.extras?.getParcelable<CharacterModel>(EXTRA_CHARACTER)
            ?: throw IllegalArgumentException("Character not found")
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            //Applied State Hoisting concept to the ScreenDetailCharacter composable.
            val context = LocalContext.current

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.color0)
                ) {
                    ScreenDetailCharacter(
                        context = context,
                        modifier = Modifier.fillMaxSize(),
                        character = character,
                        onBackPressed = {
                            (context as? Activity)?.finish()
                        }
                    )
                }
        }
    }

    companion object {

        const val EXTRA_CHARACTER = "EXTRA_CHARACTER"

        fun getCallingIntent(
            context: Context,
            characterModel: CharacterModel
        ): Intent {
            return Intent(context, DetailCharacterActivity::class.java)
                .putExtra(EXTRA_CHARACTER, characterModel)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenDetailCharacter(
    context: Context,
    character: CharacterModel,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
) {
    val characterModel by rememberSaveable {
        mutableStateOf(character)
    }

    val imagePainter = rememberImagePainter(data = characterModel.image)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        MyTopAppBar(
            character = character,
            onClickIcon = {
                when (it) {
                    "Back" -> {
                        onBackPressed()
                    }
                }
            }
        )

        Image(
            painter = imagePainter,
            contentDescription = "Character Image",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
        ) {

            Text(
                text = characterModel.name,
                color = colorResource(id = R.color.color00),
                fontFamily = MaterialTheme.typography.displayMedium.fontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = characterModel.status.id,
                modifier = Modifier
                    .padding(top = 8.dp),
                color = Color(characterModel.status.color(context)),
                fontFamily = MaterialTheme.typography.displayMedium.fontFamily,
                fontSize = 18.sp
            )

            Text(
                text = characterModel.species.id,
                modifier = Modifier
                    .padding(top = 8.dp),
                fontFamily = MaterialTheme.typography.displayMedium.fontFamily,
                fontSize = 18.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    character: CharacterModel,
    onClickIcon: (String) -> Unit
){
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorResource(id = R.color.color0),
            titleContentColor = colorResource(id = R.color.color00)
        ),
        title = {
            Text(
                text = character.name,
                color = colorResource(id = R.color.color00),
                fontFamily = MaterialTheme.typography.displayMedium.fontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                )
        },
        modifier = Modifier.shadow(7.dp),
        navigationIcon = {
            IconButton(
                onClick = {
                    onClickIcon("Back")
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icn_back),
                    contentDescription = "Back",
                    tint = colorResource(id = R.color.color_main)
                )
            }
        }
    )
}