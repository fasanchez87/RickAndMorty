package com.me.rickmorty

interface BaseClickHandler<T> {
    fun onItemClick(item: T)
}
