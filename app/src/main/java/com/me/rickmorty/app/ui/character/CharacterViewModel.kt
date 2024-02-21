package com.me.rickmorty.app.ui.character

import androidx.lifecycle.LiveData
import com.me.rickmorty.util.BaseViewModel
import com.me.rickmorty.util.ResultObject
import com.me.rickmorty.data.repository.network.CharacterRepository
import com.me.rickmorty.domain.model.CharacterModel
import com.me.rickmorty.util.toResultLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
) : BaseViewModel() {

    var listCharacter = mutableListOf<CharacterModel>()

    fun getCharacters(): LiveData<ResultObject<List<CharacterModel>>> {
        return toResultLiveData {
            characterRepository.getListCharacter()
        }
    }
}
