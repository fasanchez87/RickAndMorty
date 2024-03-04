package com.me.rickmorty.app.ui.character

import android.content.Context
import androidx.lifecycle.ViewModel
import com.me.rickmorty.app.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

//@HiltViewModel
class BaseActivityViewModel : BaseViewModel() {

    private val _context = MutableStateFlow<Context?>(null)
    val context: StateFlow<Context?> = _context

    private val _showLoading = MutableStateFlow(false)
    val showLoading: StateFlow<Boolean> = _showLoading

    private val _showAppBar = MutableStateFlow(true)
    val showAppBar: StateFlow<Boolean> = _showAppBar

    private val _titleAppBar = MutableStateFlow("title")
    val titleAppBar: StateFlow<String> = _titleAppBar

    fun setShowAppBar(show: Boolean) {
        _showAppBar.value = show
    }

    fun setTitleAppBar(title: String) {
        _titleAppBar.value = title
    }

    fun showLoading(isLoading: Boolean) {
        _showLoading.value = isLoading
    }

    fun setContext(context: Context) {
        _context.value = context
    }
}