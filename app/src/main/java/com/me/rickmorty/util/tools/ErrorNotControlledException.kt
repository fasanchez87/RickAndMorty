package com.me.rickmorty.util.tools

import com.me.rickmorty.app.ui.base.BaseException

open class ErrorNotControlledException(val title: String? = null, override var message: String? = null) : BaseException()
