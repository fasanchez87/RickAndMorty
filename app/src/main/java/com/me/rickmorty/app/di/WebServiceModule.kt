package com.me.rickmorty.app.di
import com.me.rickmorty.ConfigurationRestClient
import com.me.rickmorty.ExceptionMapper
import com.me.rickmorty.WrapperResponseMapper
import com.me.rickmorty.data.repository.network.factory.BaseRequestFactory
import com.me.rickmorty.data.repository.network.factory.RetrofitFactory
import com.me.rickmorty.data.repository.network.interceptor.ResponseInterceptor
import com.squareup.moshi.Moshi
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val webServiceModule = module {

//    single {
//        val configuration: ConfigurationRestClient = get()
//        val retrofit = get<Retrofit>()
//        BaseRequestFactory.getFactory(retrofit, configuration).getBaseRequest()
//    }
//
//    single<Converter.Factory> {
//        val moshi = get<Moshi>()
//        MoshiConverterFactory.create(moshi).withNullSerialization()
//    }
//
//    single {
//        val configuration: ConfigurationRestClient = get()
//        val responseInterceptor = get<ResponseInterceptor>()
//
//        RetrofitFactory.getFactory(
//            configuration,
//            responseInterceptor,
//            converterFactory = get()
//        ).getRetrofit()
//    }
//
//    single { WrapperResponseMapper() }
//
//    single { ExceptionMapper(get()) }
//
//    single {
//        ResponseInterceptor(get())
//    }
}
