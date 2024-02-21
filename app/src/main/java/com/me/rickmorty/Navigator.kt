package com.me.rickmorty

import android.content.Context
import android.content.Intent
import com.me.rickmorty.app.ui.character.CharactersActivity

class Navigator(context: Context) : CoreNavigator(context) {
    fun navigateToCharacters(): Intent = CharactersActivity.getCallingIntent(context).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
}
