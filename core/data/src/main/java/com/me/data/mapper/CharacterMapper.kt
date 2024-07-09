package com.me.data.mapper

import com.me.core.model.data.CharacterModel
import com.me.network.entity.CharacterEntity
import com.me.utils.mapper.IMapper

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