package com.me.rickmorty.util

interface ErrorHandleable {
    //This method is handle in responses of api in case any error
    fun onError(throwable: Throwable)
}