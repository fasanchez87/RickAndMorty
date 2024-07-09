package com.me.utils.time

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class ZonedDateTimeAdapter {
    @FromJson
    fun fromJson(created: String): ZonedDateTime {
        return ZonedDateTime.parse(created, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    }
    @ToJson
    fun toJson(createdDate: ZonedDateTime): String {
        return createdDate.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    }
}