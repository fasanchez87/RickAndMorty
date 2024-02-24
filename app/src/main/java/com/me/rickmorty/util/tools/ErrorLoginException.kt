package com.me.rickmorty.util.tools

import com.me.rickmorty.app.ui.base.BaseException

class ErrorLoginException : BaseException() {
    override val message: String
        get() = "Error login"
}
