package com.me.rickmorty

import android.view.View
import androidx.appcompat.widget.Toolbar

interface BaseToolbarListener : BaseListener {
    /* Gets the scrollable parent view from the activity to show the toolbar separator
    * Returns null if the view doesn't want to show a separator */
    fun changeScrollListener(scrollView: View?)
    fun getToolbar(): Toolbar
}
