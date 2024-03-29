package com.me.rickmorty.util.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.me.rickmorty.util.tools.ResultEvent
import com.me.rickmorty.util.tools.ResultObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

internal const val DEFAULT_TIMEOUT = 5000L

//This a extension function to convert a suspend function to a ResultObject, so we can handle the success and error cases
//Further, we can use this function to convert a suspend function to a LiveData<ResultObject> and avoid **boilerplate code

suspend fun <T> toResult(closure: suspend () -> T): ResultObject<T> {
    return try {
        ResultObject.onSuccess(closure())
    } catch (e: Throwable) {
        ResultObject.onError(e)
    }
}

suspend fun toResultEvent(closure: suspend () -> Unit): ResultEvent {
    return try {
        closure.invoke()
        ResultEvent.onSuccess()
    } catch (e: Throwable) {
        ResultEvent.onError(e)
    }
}

fun <T> toResultLiveData(
    context: CoroutineContext = EmptyCoroutineContext,
    timeoutInMs: Long = DEFAULT_TIMEOUT,
    closure: suspend () -> T
): LiveData<ResultObject<T>> {
    return liveData(context, timeoutInMs) {
        emit(
            toResult(closure)
        )
    }
}

fun toResultEventLiveData(
    context: CoroutineContext = EmptyCoroutineContext,
    timeoutInMs: Long = DEFAULT_TIMEOUT,
    closure: suspend () -> Unit
): LiveData<ResultEvent> {
    return liveData(context, timeoutInMs) {
        emit(
            toResultEvent(closure)
        )
    }
}

fun <T> Flow<T>.toResult() =
    map {
        toResult { it }
    }.catch {
        Result.failure<T>(it)
    }
