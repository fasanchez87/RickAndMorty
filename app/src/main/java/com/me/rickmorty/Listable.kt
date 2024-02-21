package com.me.rickmorty

import android.content.Context
import android.graphics.drawable.Drawable

interface Listable : Idable {
    fun getString(context: Context): String
    fun getIcon(context: Context): Drawable? = null
}
