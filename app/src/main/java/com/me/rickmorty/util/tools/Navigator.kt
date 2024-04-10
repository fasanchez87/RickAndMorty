package com.me.rickmorty.util.tools

import android.content.Context
import android.content.Intent
import com.me.rickmorty.app.ui.character.DetailCharacterActivity
import com.me.rickmorty.domain.model.CharacterModel

class Navigator(val context: Context) {

//    fun navigateToCharacters(): Intent = CharactersActivity.getCallingIntent(context)
//        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

    fun navigateToCharacterDetail(characterModel: CharacterModel): Intent = DetailCharacterActivity.getCallingIntent(context, characterModel)
}
