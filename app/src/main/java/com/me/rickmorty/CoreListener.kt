package com.me.rickmorty

import android.content.DialogInterface

interface CoreListener: ErrorHandleable {

    fun showLoading()

    fun hideLoading()

    override fun onError(throwable: Throwable)

    fun showPopup(
        title: String,
        description: String,
        cancelable: Boolean = true,
        onCancel: (() -> Unit)? = null,
        icon: Int? = null,
        image: String? = null,
        onDismiss: (() -> Unit)? = null
    )

    fun showPopup(
        title: String,
        description: String,
        positiveListener: (DialogInterface?, Int) -> Unit,
        positiveButton: String? = null,
        cancelable: Boolean = true,
        onCancel: (() -> Unit)? = null,
        icon: Int? = null,
        image: String? = null,
        onDismiss: (() -> Unit)? = null
    )

    fun showPopup(
        title: String,
        description: String,
        positiveListener: (DialogInterface?, Int) -> Unit,
        positiveButton: String? = null,
        negativeListener: ((DialogInterface?, Int) -> Unit)? = null,
        negativeButton: String? = null,
        cancelable: Boolean = true,
        onCancel: (() -> Unit)? = null,
        icon: Int? = null,
        image: String? = null,
        onDismiss: (() -> Unit)? = null
    )
}