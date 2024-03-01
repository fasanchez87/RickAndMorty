package com.me.rickmorty.app.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.me.rickmorty.app.ui.base.BaseViewModel
import com.me.rickmorty.domain.repository.CharacterRepository
import com.me.rickmorty.domain.model.CharacterModel
import com.me.rickmorty.util.extensions.toResult
import com.me.rickmorty.util.tools.ResultObject
import com.me.rickmorty.util.extensions.toResultLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
) : BaseViewModel() {

    private val _characters = MutableStateFlow<ResultObject<List<CharacterModel>>>(ResultObject.onLoading())
    val characters: StateFlow<ResultObject<List<CharacterModel>>> = _characters

    init {
        getCharactersCompose()
    }

    fun getCharacters(): LiveData<ResultObject<List<CharacterModel>>> {
        return toResultLiveData {
            characterRepository.getListCharacter()
        }
    }

    private fun getCharactersCompose() {
        viewModelScope.launch {
            _characters.value = toResult {
                characterRepository.getListCharacter()
            }
        }
    }
}
