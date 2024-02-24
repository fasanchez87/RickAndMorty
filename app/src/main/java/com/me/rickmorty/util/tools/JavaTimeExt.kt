package com.me.rickmorty.util.tools

import java.time.*
import java.util.*

const val FULL_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"
const val DATE_FORMAT = "yyyy-MM-dd"
const val TIME_FORMAT = "HH:mm:ss"

fun ZonedDateTime.parse(
    pattern: String = FULL_DATE_TIME_FORMAT,
    locale: Locale = Locale.getDefault(),
    zoneId: ZoneId = ZoneId.systemDefault()
): String = parseDateTime(this, pattern, locale, zoneId)

fun LocalDate.parse(
    pattern: String = DATE_FORMAT,
    locale: Locale = Locale.getDefault()
): String = parseDate(this, pattern, locale)

fun LocalTime.parse(
    pattern: String = TIME_FORMAT,
    locale: Locale = Locale.getDefault()
): String = parseTime(this, pattern, locale)

fun LocalDateTime.parse(
    pattern: String = FULL_DATE_TIME_FORMAT,
    locale: Locale = Locale.getDefault()
): String = parseLocalDateTime(this, pattern, locale)
