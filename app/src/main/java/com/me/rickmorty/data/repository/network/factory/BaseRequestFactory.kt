package com.me.rickmorty.data.repository.network.factory

import com.me.rickmorty.BaseRequest
import com.me.rickmorty.ConfigurationRestClient
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
