package com.me.network.factory

import com.me.network.configuration.BaseRequest
import com.me.network.configuration.ConfigurationRestClient
import retrofit2.Retrofit

class BaseRequestFactory(
    private val retrofit: Retrofit,
    private val configuration: ConfigurationRestClient
) {

    fun getBaseRequest(): BaseRequest =
        BaseRequest(
            configuration,
            retrofit
        )

    companion object {
        fun getFactory(
            retrofit: Retrofit,
            configuration: ConfigurationRestClient
        ): BaseRequestFactory {
            return BaseRequestFactory(retrofit, configuration)
        }
    }
}
