package com.me.rickmorty.app.ui.character

import androidx.lifecycle.viewModelScope
import com.me.core.model.data.CharacterModel
import com.me.data.repository.CharacterRepository
import com.me.rickmorty.app.ui.base.BaseViewModel
import com.me.rickmorty.util.extensions.toResultState
import com.me.rickmorty.util.tools.ResultObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
) : BaseViewModel() {

    private val _character = MutableStateFlow<ResultObject<CharacterModel>>(ResultObject.onLoading())
    val character: StateFlow<ResultObject<CharacterModel>> = _character.asStateFlow()

    fun getCharacter(id: String) {
        viewModelScope.launch {
            toResultState {
                characterRepository.getCharacterById(id)
            }.collect { result ->
                _character.value = result
            }
        }
    }
}


