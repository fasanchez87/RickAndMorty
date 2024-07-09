package com.me.utils.exception

class ErrorLoginException : BaseException() {
    override val message: String
        get() = "Error login"
}

