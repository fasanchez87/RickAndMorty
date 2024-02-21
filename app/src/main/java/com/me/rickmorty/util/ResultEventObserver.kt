package com.me.rickmorty.util
import androidx.lifecycle.Observer

abstract class ResultEventObserver(private val listener: ErrorHandleable) : Observer<ResultEvent> {
    final override fun onChanged(value: ResultEvent) = if (value is ResultEvent.ErrorEvent) onError(value.t) else onSuccess()
    abstract fun onSuccess()
    open fun onError(ex: Throwable) = listener.onError(ex)
}
