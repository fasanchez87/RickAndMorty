package com.me.utils.exception

open class ErrorNotControlledException(val title: String? = null, override var message: String? = null) : BaseException()
