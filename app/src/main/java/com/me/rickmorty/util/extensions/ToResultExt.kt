package com.me.rickmorty.util.extensions

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.repeatOnLifecycle
import com.me.rickmorty.util.tools.ErrorLoginException
import com.me.rickmorty.util.tools.ResultObject
import com.me.rickmorty.util.tools.ShowMessageException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.net.UnknownHostException
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

internal const val DEFAULT_TIMEOUT = 5000L

//This a extension function to convert a suspend function to a ResultObject, so we can handle the success and error cases
//Further, we can use this function to convert a suspend function to a LiveData<ResultObject> and avoid **boilerplate code

@Composable
fun <@Composable T> ObserveStateFlow(
    stateFlow: LiveData<ResultObject<T>>,
    onSuccess: @Composable (T) -> Unit,
    onError: (Throwable) -> Unit = { },
    onEmpty: () -> Unit  = { },
    onLoading: @Composable () -> Unit,
    context: Context,
    lifecycleOwner: LifecycleOwner
) {

    val result = remember {
        mutableStateOf<ResultObject<T>>(ResultObject.LoadingObject())
    }

    LaunchedEffect(result, lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            stateFlow.observe(lifecycleOwner) {
                result.value = it
            }
        }
    }

    when (val result = result.value) {
        is ResultObject.SuccessObject -> {
            onSuccess(result.data)
        }

        is ResultObject.ErrorObject -> {
            onError(result.t)
            HandleError(result.t, context) {}
        }

        is ResultObject.EmptyObject -> {
            onEmpty()
        }

        is ResultObject.LoadingObject -> {
            onLoading()
        }
    }
}

@Composable
fun HandleError(
    throwable: Throwable,
    context: Context,
    onDismiss: () -> Unit
) {
    var showDialog by remember {
        mutableStateOf(false)
    }

    var dialogMessage by remember {
        mutableStateOf("")
    }

    throwable.printStackTrace()

    LaunchedEffect(throwable) {
        when (throwable) {
            is ErrorLoginException -> {
                //navigator.navigateToLogin()?.let { startActivity(it) }
            }
            is ShowMessageException -> {

            }
            is UnknownHostException -> {
                showDialog = true
                dialogMessage = "No internet"
            }
            else -> {
                Timber.tag("Error").e(throwable, "Error")
                // OnErrorDefaultCase(throwable, onDismiss)
            }
        }
    }

    if (showDialog) {
        ShowDialog(
            title = "Error",
            description = dialogMessage,
            onDismiss = {
                Toast.makeText(context, "Dismiss", Toast.LENGTH_SHORT).show()
                showDialog = false
                onDismiss()
            }
        )
    }
}

@Composable
fun ShowDialog(
    title: String,
    description: String,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Card(
            modifier = Modifier
                .testTag("dialog")
                .fillMaxWidth(),
            colors = CardDefaults
                .cardColors(
                    containerColor = Color.White
                ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Add Task",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.End)
                ) {
                    TextButton(
                        onClick = {
                            onDismiss()
                        }
                    ) {
                        Text(text = "Cancel")
                    }
                    TextButton(
                        onClick = {

                        },
                        enabled = true
                    ) {
                        Text(text = "Save")
                    }
                }
            }
        }
    }
}

suspend fun <T> toResult(closure: suspend () -> T): ResultObject<T> {
    return try {
        ResultObject.onSuccess(closure())
    } catch (e: Throwable) {
        ResultObject.onError(e)
    }
}
//
//suspend fun toResultEvent(closure: suspend () -> Unit): ResultEvent {
//    return try {
//        closure.invoke()
//        ResultEvent.onSuccess()
//    } catch (e: Throwable) {
//        ResultEvent.onError(e)
//    }
//}

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
//
//fun toResultEventLiveData(
//    context: CoroutineContext = EmptyCoroutineContext,
//    timeoutInMs: Long = DEFAULT_TIMEOUT,
//    closure: suspend () -> Unit
//): LiveData<ResultEvent> {
//    return liveData(context, timeoutInMs) {
//        emit(
//            toResultEvent(closure)
//        )
//    }
//}

fun <T> Flow<T>.toResult() =
    map {
        toResult { it }
    }.catch {
        Result.failure<T>(it)
    }

