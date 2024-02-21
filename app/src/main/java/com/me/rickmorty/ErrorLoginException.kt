package com.me.rickmorty

class ErrorLoginException : BaseException() {
    override val message: String
        get() = "Error login"
}
