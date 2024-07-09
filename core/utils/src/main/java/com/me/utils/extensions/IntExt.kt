package com.me.utils.extensions

import android.content.Context
import androidx.core.content.ContextCompat

fun Int.asResourceColor(context: Context) = ContextCompat.getColor(context, this)
