package com.me.rickmorty.util.tools

import com.me.rickmorty.app.ui.base.BaseException
import okhttp3.CertificatePinner
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response

interface ConfigurationRestClient {

    val exceptionMapper: ExceptionMapper

    val basePath: String

    val logLevel: HttpLoggingInterceptor.Level
        get() = HttpLoggingInterceptor.Level.BODY

    val timeout: Long
        get() = 30L

    fun certification(): (CertificatePinner.Builder.() -> Unit)? = null

    fun <T> hasHttpError(response: Response<T>): BaseException?

    fun hasResponseError(body: String): BaseException? = null

    fun handleResponse(response: String): String =
        exceptionMapper.wrapperResponseMapper.getData(response)

    suspend fun logPetition(
        headers: String,
        bodyParam: String,
        path: String,
        method: String,
        responseBody: String
    ) {
//        crashReports.setString(PARAM_CRASHLYTICS_KEY_HEADERS, headers)
//        crashReports.setString(PARAM_CRASHLYTICS_KEY_BODY_PARAM, bodyParam)
//        crashReports.setString(PARAM_CRASHLYTICS_KEY_PATH, path)
//        crashReports.setString(PARAM_CRASHLYTICS_KEY_RESPONSE, responseBody)
//        crashReports.setString(PARAM_CRASHLYTICS_KEY_METHOD, method)
    }
}
