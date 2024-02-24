package com.me.rickmorty.data.mapper

import com.me.rickmorty.util.tools.IMapper
import com.me.rickmorty.data.entity.CharacterEntity
import com.me.rickmorty.domain.model.CharacterModel

class CharacterMapper: IMapper<CharacterEntity, CharacterModel> {
    override fun toModel(entity: CharacterEntity): CharacterModel =
        with(entity) {
            CharacterModel(
                id,
                name,
                CharacterModel.Status.findById(status),
                CharacterModel.Species.findById(species),
                type,
                CharacterModel.Gender.findById(gender),
                image,
                url,
                CharacterModel.getDateText(created)
            )
        }
}