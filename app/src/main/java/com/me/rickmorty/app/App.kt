package com.me.rickmorty.app

import android.app.Application
import com.google.gson.GsonBuilder
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@HiltAndroidApp
class App: Application() {

    companion object {

        var SCREEN_WIDTH: Int = 0
        var SCREEN_HEIGHT: Int = 0

        private var INSTANCE: App? = null

        @JvmStatic
        fun get(): App = INSTANCE!!

        val gsonSingleton: GsonBuilder by lazy { GsonBuilder() }

        val moshiSingleton: Moshi.Builder by lazy { Moshi.Builder().add(ZonedDateTimeAdapter()) }
    }

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

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}