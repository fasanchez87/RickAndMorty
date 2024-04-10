package com.me.rickmorty.util.tools

sealed class ResultObject<T> {

    companion object {
        fun <T> onSuccess(data: T): ResultObject<T> = SuccessObject(data)
        fun <T> onError(e: Throwable): ResultObject<T> = ErrorObject(e)
        fun <T> onEmpty(): ResultObject<T> = EmptyObject()
        fun <T> onLoading(): ResultObject<T> = LoadingObject()
    }

    data class SuccessObject<T>(var data: T) : ResultObject<T>()
    data class ErrorObject<T>(val t: Throwable) : ResultObject<T>()
    class EmptyObject<T> : ResultObject<T>()
    class LoadingObject<T> : ResultObject<T>()

    open fun isError(): Boolean = this is ErrorObject
    open fun isSuccess(): Boolean = this is SuccessObject

    /**
     * return value or exception
     * */
    fun getValueOrError(): T {
        if (this is SuccessObject<T> && data != null) {
            return data
        } else if (this is ErrorObject<T>) {
            throw t
        } else {
            throw IllegalStateException("This value is fail or Suceess cannot be get value from failure")
        }
    }

    /**
     * return value or null
     * */
    fun getOrNull(): T? {
        return if (this is SuccessObject<T>) {
            data
        } else {
            null
        }
    }

    fun getExceptionOrNull(): Throwable? {
        if (this is ErrorObject) {
            return t
        }
        return null
    }

    /**
     * Right-biased map() FP convention which means that Right is assumed to be the default case
     * to operate on. If it is Left, operations like map, flatMap, ... return the Left value unchanged.
     */
    fun <R> map(mapper: (T) -> (R)): ResultObject<R> = this.flatMap { SuccessObject(mapper(it)) }

    fun <R> flatMap(fn: (T) -> ResultObject<R>): ResultObject<R> =
        when (this) {
            is ErrorObject -> ErrorObject(t)
            is SuccessObject -> fn(data)
            else -> ErrorObject(IllegalStateException("We can't do a flat map in ${this::class.java.simpleName} object"))
        }

    suspend fun <R> flatMapSuspend(fn: (T) -> ResultObject<R>): ResultObject<R> =
        when (this) {
            is ErrorObject -> ErrorObject(t)
            is SuccessObject -> fn(data)
            else -> ErrorObject(IllegalStateException("We can't do a flat map in ${this::class.java.simpleName} object"))
        }
}
//
//sealed class ResultEvent : ResultObject<Unit>() {
//
//    class SuccessEvent : ResultEvent()
//
//    data class ErrorEvent(val t: Throwable) : ResultEvent()
//
//    override fun isError(): Boolean = this is ErrorEvent
//
//    override fun isSuccess(): Boolean = this is SuccessEvent
//
//    companion object {
//        fun onSuccess(): ResultEvent = SuccessEvent()
//        fun onError(e: Throwable): ResultEvent = ErrorEvent(e)
//    }
//}
