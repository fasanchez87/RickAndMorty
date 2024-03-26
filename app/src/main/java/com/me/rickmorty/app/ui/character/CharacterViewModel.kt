package com.me.rickmorty.app.ui.character

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.me.rickmorty.app.ui.base.BaseViewModel
import com.me.rickmorty.domain.repository.CharacterRepository
import com.me.rickmorty.domain.model.CharacterModel
import com.me.rickmorty.util.extensions.toResult
import com.me.rickmorty.util.tools.ResultObject
import com.me.rickmorty.util.extensions.toResultLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
       //getCharactersCompose()
    }

    fun getCharacters(): LiveData<ResultObject<List<CharacterModel>>> {
        return toResultLiveData {
            characterRepository.getListCharacter()
        }
    }

    fun getCharactersCompose(): StateFlow<ResultObject<List<CharacterModel>>> {
        viewModelScope.launch {
            _characters.value = toResult {
                characterRepository.getListCharacter()
            }
        }
        return characters
    }

}
