package com.me.rickmorty.data.repository.network.factory

import com.me.rickmorty.ConfigurationRestClient
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RetrofitFactory @Inject constructor(
    private val configuration: ConfigurationRestClient,
    private val responseInterceptor: Interceptor? = null,
    private val converterFactory: Converter.Factory? = GsonConverterFactory.create()
) {

    fun getRetrofit(): Retrofit {
        val okhttpClent = OkHttpClient.Builder().apply() {
            readTimeout(configuration.timeout, TimeUnit.SECONDS)
            connectTimeout(configuration.timeout, TimeUnit.SECONDS)
            writeTimeout(configuration.timeout, TimeUnit.SECONDS)

            val cookieManager = CookieManager()
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)

            responseInterceptor?.let {
                addInterceptor(responseInterceptor)
            }

            val loginInterceptor = HttpLoggingInterceptor()
            loginInterceptor.level = configuration.logLevel
            addInterceptor(loginInterceptor)

            configuration.certification()?.let { function ->
                certificatePinner(
                    CertificatePinner.Builder()
                        .apply {
                            function()
                        }.build()
                )
            }
        }.build()

        return Retrofit.Builder()
            .baseUrl(configuration.basePath)
            .addConverterFactory(converterFactory!!)
            .client(okhttpClent)
            .build()
    }

    companion object {
        fun getFactory(
            configuration: ConfigurationRestClient,
            responseInterceptor: Interceptor? = null,
            converterFactory: Converter.Factory? = GsonConverterFactory.create()
        ): RetrofitFactory {
            return RetrofitFactory(configuration, responseInterceptor, converterFactory)
        }
    }
}
