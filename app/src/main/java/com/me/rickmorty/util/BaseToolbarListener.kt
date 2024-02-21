package com.me.rickmorty.util

import android.view.View
import androidx.appcompat.widget.Toolbar

interface BaseToolbarListener : CoreListener {
    fun changeScrollListener(scrollView: View?)
    fun getToolbar(): Toolbar
}
