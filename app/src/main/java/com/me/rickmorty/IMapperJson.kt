package com.me.rickmorty

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import java.lang.reflect.ParameterizedType

interface IMapperJson<Entity, Model> : IMapper<Entity, Model>, KoinComponent {

    val moshi: Moshi
        get() = get()

    val serializeNulls: Boolean
        get() = true

    fun getClassEntity(): Class<Entity>

    private fun getClassListEntity(): ParameterizedType = Types.newParameterizedType(List::class.java, getClassEntity())

    fun toEntity(json: String): Entity = try {
        moshi.adapter(getClassEntity()).let { if (serializeNulls) it.serializeNulls() else it }.fromJson(json)!!
    } catch (e: Throwable) {
        throw MapperException(e)
    }

    fun entityToJson(entity: Entity): String = moshi.adapter(getClassEntity()).let { if (serializeNulls) it.serializeNulls() else it }.toJson(entity)

    fun toModel(json: String): Model = toModel(toEntity(json))

    fun modelToJson(model: Model): String = entityToJson(toEntity(model))

    fun entityToJsonArray(entities: MutableList<Entity>): String = moshi.adapter<List<Entity>>(getClassListEntity()).let { if (serializeNulls) it.serializeNulls() else it }.toJson(entities)

    fun toListEntity(jsonArray: String): MutableList<Entity> =
        try {
            moshi.adapter<List<Entity>>(getClassListEntity()).let { if (serializeNulls) it.serializeNulls() else it }.fromJson(jsonArray)!!.toMutableList()
        } catch (e: Throwable) {
            throw MapperException(e)
        }

    fun modelToJsonArray(models: MutableList<Model>): String = entityToJsonArray(toListEntity(models))

    fun toListModel(jsonArray: String): MutableList<Model> = toListModel(toListEntity(jsonArray))
}
