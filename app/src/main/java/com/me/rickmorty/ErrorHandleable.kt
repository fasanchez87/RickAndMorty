package com.me.rickmorty

interface ErrorHandleable {
    //This method is handle in responses of api in case any error
    fun onError(throwable: Throwable)
}