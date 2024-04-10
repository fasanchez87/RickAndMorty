package com.me.rickmorty.app.ui.character

import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.me.rickmorty.app.ui.base.BaseViewModel
import com.me.rickmorty.domain.model.CharacterModel
import com.me.rickmorty.domain.repository.CharacterRepository
import com.me.rickmorty.util.extensions.toResultLiveData
import com.me.rickmorty.util.extensions.toResultState
import com.me.rickmorty.util.tools.ResultObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
) : BaseViewModel() {

    private val _characters = MutableStateFlow<ResultObject<List<CharacterModel>>>(ResultObject.onLoading())
    val characters: StateFlow<ResultObject<List<CharacterModel>>> = _characters.asStateFlow()

    init {
        getCharacters()
    }

    fun addCharacter(character: CharacterModel) {
        val currentCharacters = _characters.value.getOrNull() ?: emptyList()
        val updatedCharacters = currentCharacters + character
        _characters.value = ResultObject.onSuccess(updatedCharacters)
    }

    //This method return a StateFlow, so the UI can recompose and show the data; this is useful when you want to show the data update in real time
    //So, the UI can recompose and show the new data; for example, if you go to another screen and come back, the data will be updated*
//    fun getCharacters(): StateFlow<ResultObject<List<CharacterModel>>> {
//        return toResultLiveData {
//            //_characters.value = toResult { characterRepository.getListCharacter() }
//            characterRepository.getListCharacter()
//        }
//        .asFlow()
//        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ResultObject.onLoading())
//    }

//    fun getCharacters() =
//        toResultLiveData {
//            characterRepository.getListCharacter()
//        }

    fun getCharacters() {
        Timber.tag("CharacterViewModel").d("getCharacters")
        viewModelScope.launch {
            toResultState {
                characterRepository.getListCharacter()
            }.collect { result ->
                _characters.value = result
            }
        }
    }
}
