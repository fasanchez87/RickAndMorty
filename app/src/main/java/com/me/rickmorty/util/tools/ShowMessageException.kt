package com.me.rickmorty.util.tools

import com.me.rickmorty.app.ui.base.BaseException

open class ShowMessageException(val text: String, val title: String? = null) : BaseException()
