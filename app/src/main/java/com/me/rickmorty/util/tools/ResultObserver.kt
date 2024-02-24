package com.me.rickmorty.util.tools

import androidx.lifecycle.Observer

abstract class ResultObserver<T>(private val listener: ErrorHandleable) : Observer<ResultObject<T>> {

    final override fun onChanged(value: ResultObject<T>) {
        when (value) {
            is ResultObject.SuccessObject<T> -> onReceived(value.data)
            is ResultObject.ErrorObject<T> -> onError(value.t)
            is ResultObject.EmptyObject<T> -> onEmpty()
            else -> {
                onError(Throwable("Unknown result type"))
            }
        }
    }

    abstract fun onReceived(t: T)
    open fun onError(ex: Throwable) = listener.onError(ex)
    open fun onEmpty() {}
}
