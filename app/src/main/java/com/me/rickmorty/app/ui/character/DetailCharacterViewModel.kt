package com.me.rickmorty.app.ui.character

import com.me.rickmorty.app.ui.base.BaseViewModel
import com.me.rickmorty.domain.model.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

//@HiltViewModel
//class DetailCharacterViewModel : BaseViewModel() {
//
//    private val _character = MutableStateFlow(CharacterModel.returnCharacterNull())
//    val character: StateFlow<CharacterModel> = _character
//
//    fun getCharacter() = _character.value
//}
