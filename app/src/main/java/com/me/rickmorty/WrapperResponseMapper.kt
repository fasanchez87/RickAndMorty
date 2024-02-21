package com.me.rickmorty

import com.google.gson.JsonParser

open class WrapperResponseMapper {

    open fun getCommons(response: String): String? {
        val commons = JsonParser.parseString(response).asJsonObject.get("commons")
        return if (!commons.isJsonNull) commons.asJsonObject.toString() else null
    }

    open fun getData(response: String): String {
        return JsonParser.parseString(response).asJsonObject.get("data").toString()
    }
}
