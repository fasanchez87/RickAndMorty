package com.me.rickmorty.data.repository.network.interceptor
import WrapperIOException
import com.me.rickmorty.ConfigurationRestClient
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject

class ResponseInterceptor @Inject constructor(
    val configuration: ConfigurationRestClient
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val request = chain.request()
        val responseResultRaw: String? = response.body?.string()

        return try {
            val responseValue = if (response.isSuccessful)
                responseResultRaw?.let { string ->
                    runBlocking {
                        configuration.hasResponseError(string)?.let {
                            throw it
                        } ?: run {
                            val data = configuration.handleResponse(string)
                            response.newBuilder().body(
                                data.toResponseBody()
                            ).build()
                        }
                    }
                } ?: response
            else response.newBuilder().body(
                responseResultRaw.toString().toResponseBody()
            ).build()

            val headers = request.headers.toString()
            val bodyParam = request.body?.toString() ?: ""
            val path = request.url.toString()
            val method = request.method

            runBlocking {
                configuration.logPetition(
                    headers, bodyParam, path, method, responseResultRaw ?: ""
                )
            }
            responseValue
        } catch (e: Throwable) {
            // Lo envuelvo en un IOException porque Okhttp devuelve SIEMPRE un IOException cuando hay crash en los interceptors, excepto si ya es un IOException. De esta manera podemos mandar nuestra excepción de forma anidada.
            throw WrapperIOException(e)
        }
    }
}
