package com.me.rickmorty.util

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard(view: View?) {
    val imm = (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
    view?.let {
        imm.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun Activity.hideKeyboard() = hideKeyboard(currentFocus)
