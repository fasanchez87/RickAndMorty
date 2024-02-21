package com.me.rickmorty.util

import org.json.JSONObject

open class ExceptionMapper(val wrapperResponseMapper: WrapperResponseMapper) {

    open fun getShowMessageException(response: String): ShowMessageException {
        val data = JSONObject(wrapperResponseMapper.getData(response))
        val message = data.get("message") as String? ?: ""
        return ShowMessageException(message)
    }
}
