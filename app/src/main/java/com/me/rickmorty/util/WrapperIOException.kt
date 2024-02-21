package com.me.rickmorty.util

import java.io.IOException
/**
 Exception to wrap possible exceptions produced within an okhttp interceptor, which must be an IOException per library requirement
 */
class WrapperIOException(t: Throwable) : IOException(t)
