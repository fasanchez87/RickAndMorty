package com.me.rickmorty.util

class ErrorLoginException : BaseException() {
    override val message: String
        get() = "Error login"
}
