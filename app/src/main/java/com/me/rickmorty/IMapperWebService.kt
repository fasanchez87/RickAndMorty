package com.me.rickmorty

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import java.lang.reflect.Type

interface IMapperWebService<Entity, Model> : IMapper<Entity, Model>, KoinComponent {

    val gson: Gson
        get() = get()

    fun getClassEntity(): Class<Entity> // (Entity::class.java)
    fun getClassListEntity(): Type // (object : TypeToken<List<Entity>>(){}.type)

    fun toEntity(jsonElement: JsonObject): Entity = gson.fromJson<Entity>(jsonElement, getClassEntity())

    fun entityToJson(entity: Entity): JsonObject = gson.toJsonTree(entity, getClassEntity()).asJsonObject

    fun toModel(jsonObject: JsonObject): Model = toModel(toEntity(jsonObject))

    fun modelToJson(model: Model): JsonObject = entityToJson(toEntity(model))

    fun entityToJsonArray(entities: MutableList<Entity>): JsonArray = gson.toJsonTree(entities, object : TypeToken<MutableList<Entity>>() {}.type).asJsonArray

    fun toListEntity(jsonArray: JsonArray): MutableList<Entity> = gson.fromJson<List<Entity>>(jsonArray, getClassListEntity()).toMutableList()

    fun modelToJsonArray(models: MutableList<Model>): JsonArray = entityToJsonArray(toListEntity(models))

    fun toListModel(jsonArray: JsonArray): MutableList<Model> = toListModel(toListEntity(jsonArray))

    fun toEntity(json: String): Entity = toEntity(JsonParser.parseString(json).asJsonObject)

    fun toModel(json: String): Model = toModel(toEntity(json))
}
