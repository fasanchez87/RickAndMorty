package com.me.rickmorty.util

import android.view.View
import androidx.fragment.app.DialogFragment

interface CustomDialogable {
    fun setView(view: View)
    fun asDialogFragment(): DialogFragment
}
