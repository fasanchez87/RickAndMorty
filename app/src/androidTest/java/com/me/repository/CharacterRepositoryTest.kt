package com.me.repository

import com.me.rickmorty.domain.model.CharacterModel
import com.me.rickmorty.domain.repository.CharacterRepository

class CharacterRepositoryTest: CharacterRepository {

    private var characterList: List<CharacterModel> = listOf(
        CharacterModel(
            "1",
            "Rick",
            CharacterModel.Status.ALIVE,
            CharacterModel.Species.ALIEN,
            "Earth",
            CharacterModel.Gender.FEMALE,
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        ),
        CharacterModel(
            "2",
            "Martin",
            CharacterModel.Status.ALIVE,
            CharacterModel.Species.ALIEN,
            "Earth",
            CharacterModel.Gender.FEMALE,
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        )
    )

    override suspend fun getListCharacter(): List<CharacterModel> {
        return characterList
    }
}