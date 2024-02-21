package com.me.rickmorty

import okhttp3.CertificatePinner
import okhttp3.Headers
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

    fun <T> hasHttpError(response: Response<T>): BaseException? {
        return when (response.code()) {
            418 -> {
                response.errorBody()?.string()?.let {
                    exceptionMapper.getShowMessageException(it)
                } ?: ShowMessageException("Message field not found")
            }
            else -> null
        }
    }

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

    /**
     * Indicate that this petition is authenticate or not
     * It means if for example login get an authenticator error the controller doesn't execute refrestoken
     *
     * By default the library search token at the headers, but this function will be exposed to overrides if it needs.
     * @param url complete url will
     * @param headers all headers petition
     */
    fun isPetitionAuth(url: String, headers: Headers): Boolean = headers["token"] != null

    fun isRefreshTokenCall(url: String): Boolean = url.contains("refreshToken")

    val hasAuthenticatorProtocol: Boolean
        get() = true

    suspend fun refreshToken() {}

    companion object {

        // Headers constant
        const val PARAM_HEADER_DEVICE_TYPE = "deviceType"
        const val PARAM_HEADER_DEVICE_ID = "deviceId"

        // Headers debug constant
        const val PARAM_HEADER_DEVICE_MODEL = "deviceModel"
        const val PARAM_HEADER_DEVICE_OS = "deviceOs"
        const val PARAM_HEADER_DEVICE_VERSION = "deviceVersion"
        const val PARAM_HEADER_KEY_WIFI_OR_NETWORK_DATA = "deviceNetwork"

        // Crashlytics debug keys
        const val PARAM_CRASHLYTICS_KEY_BODY_PARAM = "bodyParam"
        const val PARAM_CRASHLYTICS_KEY_PATH = "path"
        const val PARAM_CRASHLYTICS_KEY_RESPONSE = "response"
        const val PARAM_CRASHLYTICS_KEY_METHOD = "method"
        const val PARAM_CRASHLYTICS_KEY_HEADERS = "headers"
    }
}
