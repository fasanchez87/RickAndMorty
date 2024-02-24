package com.me.rickmorty.app.ui.base

import android.view.View
import androidx.appcompat.widget.Toolbar
import com.me.rickmorty.util.tools.CoreListener

interface BaseToolbarListener : CoreListener {
    fun changeScrollListener(scrollView: View?)
    fun getToolbar(): Toolbar
}
