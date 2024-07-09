package com.me.network.configuration

import com.me.network.BuildConfig
import com.me.utils.exception.BaseException
import com.me.utils.exception.ExceptionMapper
import com.me.utils.exception.ShowMessageException
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import javax.inject.Inject

class ConfigurationRestClientImpl @Inject constructor(
    override val exceptionMapper: ExceptionMapper
)  : ConfigurationRestClient {

    override val basePath: String = BuildConfig.BASE_PATH

    override val logLevel: HttpLoggingInterceptor.Level
        get() =
            if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE

    override fun handleResponse(response: String) = response

    override fun <T> hasHttpError(response: Response<T>): BaseException? {
        return when (response.code()){
            403 -> response.errorBody()?.string()?.let {
                exceptionMapper.getShowMessageException(it)
            } ?: ShowMessageException("Message field not found")
            else -> null
        }
    }
}
