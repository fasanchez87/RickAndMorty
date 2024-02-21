package com.me.rickmorty

open class ErrorNotControlledException(val title: String? = null, override var message: String? = null) : BaseException()
