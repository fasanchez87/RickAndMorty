package com.me.rickmorty.util.tools

interface ErrorHandleable {
    //This method is handle in responses of api in case any error
    fun onError(throwable: Throwable)
}