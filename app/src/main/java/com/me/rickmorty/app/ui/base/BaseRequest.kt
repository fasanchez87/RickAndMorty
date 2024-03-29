package com.me.rickmorty.app.ui.base

import com.me.rickmorty.util.tools.ConfigurationRestClient
import com.me.rickmorty.util.tools.ErrorNotControlledException
import com.me.rickmorty.util.tools.WrapperIOException
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

open class BaseRequest @Inject constructor(
    private val configuration: ConfigurationRestClient,
    val retrofit: Retrofit
) {

    suspend fun <T> request(
        call: suspend () -> Response<T>
    ): T =
        try {
            call().let { response ->
                if (response.isSuccessful && response.body() != null) {
                    response.body()!!
                }
                else {
                    throw configuration.hasHttpError(response) ?: ErrorNotControlledException("Error", "${response.code()} => ${response.errorBody()}")
                }
            }
        }
        catch (e: WrapperIOException) {
            throw e.cause!!
        }
}
