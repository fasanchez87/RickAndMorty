package com.me.rickmorty.util

open class ErrorNotControlledException(val title: String? = null, override var message: String? = null) : BaseException()
