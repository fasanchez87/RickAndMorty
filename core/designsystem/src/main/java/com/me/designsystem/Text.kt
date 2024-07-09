package com.me.designsystem

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//@Composable
//fun Text(
//text = character.name,
//fontSize = 16.sp,
//modifier = Modifier
//.fillMaxWidth()
//.padding(bottom = 3.dp),
//fontFamily = FontFamily(Font(R.font.montserrat_semibold))
//)

@Composable
fun MeText(
    text: String,
    fontSize: Int,
    modifier: Modifier = Modifier,
    isFillMaxWidth: Boolean = false,
    padding: Dp = 0.dp,
    fontFamily: FontFamily = fontMonserratSemiBold()
){
    val widthModifier = if (isFillMaxWidth) Modifier.fillMaxWidth() else Modifier

    Text(
        text = text,
        modifier = modifier
            .then(widthModifier)
            .padding(bottom = padding),
        fontSize = fontSize.dp.value.sp,
        fontFamily = fontFamily
    )
}

@Preview(showBackground = true)
@Composable
fun MeTextPreview(){
    MeText(
        text = "Hello World",
        fontSize = 26,
        modifier = Modifier,
        padding = 3.dp,
        isFillMaxWidth = true,
        fontFamily = fontMonserratBold()
    )
}