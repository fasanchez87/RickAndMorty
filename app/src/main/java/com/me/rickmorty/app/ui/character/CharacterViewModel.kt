package com.me.rickmorty.app.ui.character

import androidx.lifecycle.LiveData
import com.me.rickmorty.app.ui.base.BaseViewModel
import com.me.rickmorty.domain.repository.CharacterRepository
import com.me.rickmorty.domain.model.CharacterModel
import com.me.rickmorty.util.tools.ResultObject
import com.me.rickmorty.util.extensions.toResultLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
) : BaseViewModel() {

    fun getCharacters(): LiveData<ResultObject<List<CharacterModel>>> {
        return toResultLiveData {
            characterRepository.getListCharacter()
        }
    }
}
