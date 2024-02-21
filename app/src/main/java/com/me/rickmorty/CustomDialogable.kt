package com.me.rickmorty

import android.view.View
import androidx.fragment.app.DialogFragment

interface CustomDialogable {
    fun setView(view: View)
    fun asDialogFragment(): DialogFragment
}
