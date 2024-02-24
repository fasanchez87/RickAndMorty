package com.me.rickmorty.util.extensions

import com.me.rickmorty.util.tools.parse
import java.time.ZonedDateTime

const val FULL_DATE_FORMAT = "dd/MM/yyyy HH:mm"

fun parseFullDate(date: ZonedDateTime?, pattern: String = FULL_DATE_FORMAT) =
    date?.parse(pattern)
