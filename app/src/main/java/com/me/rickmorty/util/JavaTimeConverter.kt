
package com.me.rickmorty.util

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import java.util.*

/**
 * DateTime = ZonedDateTime / Contains all information about the local time, like schedule summer and grenwich changes, this is the class most used.
 * Date = LocalDate / Contains information about a date, without taking into account the offset time and schedule summer.
 * Time = LocalTime contains the information about an hour, without taking into account grenwich deplacement. Must be sended by server in the local hour
 * */

private fun getSimpleDateFormat(pattern: String, locale: Locale = Locale.getDefault()) = DateTimeFormatter.ofPattern(pattern, locale)

/** Functions to format with zone **/
fun parseDateTime(date: String, pattern: String = FULL_DATE_TIME_FORMAT, locale: Locale = Locale.getDefault(), zoneId: ZoneId = ZoneId.of("Etc/UTC"), localZone: ZoneId = ZoneId.systemDefault()): ZonedDateTime =
    ZonedDateTime.parse(date, getSimpleDateFormat(pattern, locale).withZone(zoneId)).withZoneSameInstant(localZone)

fun parseDateTime(date: TemporalAccessor, pattern: String = FULL_DATE_TIME_FORMAT, locale: Locale = Locale.getDefault(), zoneId: ZoneId = ZoneId.of("Etc/UTC")): String =
    getSimpleDateFormat(pattern, locale).withZone(zoneId).format(date)

fun parseUnix(date: String): ZonedDateTime = parseDateTime(date)
fun parseUnix(date: TemporalAccessor): String = parseDateTime(date)

/** Local Date and time without taking into account hour and timezone */
fun parseLocalDateTime(date: String, pattern: String = FULL_DATE_TIME_FORMAT, locale: Locale = Locale.getDefault()) =
    LocalDateTime.parse(date, getSimpleDateFormat(pattern, locale))

fun parseLocalDateTime(date: TemporalAccessor, pattern: String = FULL_DATE_TIME_FORMAT, locale: Locale = Locale.getDefault()) =
    getSimpleDateFormat(pattern, locale).format(date)

fun parseUnixLocalDateTime(date: String): LocalDateTime = parseLocalDateTime(date)
fun parseUnixLocalDateTime(date: TemporalAccessor): String = parseLocalDateTime(date)

/** Local date without taking into account hour and timezone **/
fun parseDate(time: String, pattern: String = DATE_FORMAT, locale: Locale = Locale.getDefault()): LocalDate =
    LocalDate.parse(time, getSimpleDateFormat(pattern, locale))

fun parseDate(date: TemporalAccessor, pattern: String = DATE_FORMAT, locale: Locale = Locale.getDefault()): String =
    getSimpleDateFormat(pattern, locale).format(date)

fun parseUnixDate(time: String): LocalDate = parseDate(time)
fun parseUnixDate(date: TemporalAccessor): String = parseDate(date)

/** Functions to format date **/
fun parseTime(time: String, pattern: String = TIME_FORMAT, locale: Locale = Locale.getDefault()): LocalTime =
    LocalTime.parse(time, getSimpleDateFormat(pattern, locale))

fun parseTime(time: TemporalAccessor, pattern: String = TIME_FORMAT, locale: Locale = Locale.getDefault()): String =
    getSimpleDateFormat(pattern, locale).format(time)

fun parseUnixTime(time: String): LocalTime = parseTime(time)
fun parseUnixTime(time: TemporalAccessor): String = parseTime(time)
