package com.me.rickmorty

import android.util.Log

interface BaseLogger {

    fun getTag() = "--->"

    fun setUser(user: String) {
        TODO("Not implemented")
    }

    fun info(message: String) {
        Log.i(getTag(), message)
    }

    fun debug(message: String, throwable: Throwable? = null) {
        Log.d(getTag(), message, throwable)
    }

    fun error(throwable: Throwable) {
        Log.e(getTag(), throwable.message, throwable)
    }

    fun error(message: String, throwable: Throwable? = null) {
        Log.e(getTag(), message, throwable)
    }

    fun warn(message: String, throwable: Throwable? = null) {
        Log.w(getTag(), message, throwable)
    }

    fun verbose(message: String, throwable: Throwable? = null) {
        Log.v(getTag(), message, throwable)
    }
}
