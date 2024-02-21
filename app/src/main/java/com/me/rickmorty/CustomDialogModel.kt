package com.me.rickmorty

import android.os.Parcelable
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.me.rickmorty.adapter.BaseAdapter
import kotlinx.parcelize.Parcelize

@Parcelize
class CustomDialogModel(var positiveButton: String) : Parcelable {
    @DrawableRes
    var icon: Int? = null
    var image: String? = null
    var title: CharSequence? = null
    var message: CharSequence? = null
    var positiveButtonListener: (() -> Unit)? = null
    var positiveButtonContentDescription: String? = null
    var negativeButton: String? = null
    var negativeButtonContentDescription: String? = null
    var negativeButtonListener: (() -> Unit)? = null
    var hideButtons: Boolean = false
    var hideClose: Boolean = false
    var closeContentDescription: String? = null
    var enableEditText: Boolean = false
    var editTextHint: String? = null
    var editTextInitText: String? = null
    var editTextListener: ((String, TextView) -> Unit)? = null
    var editTextImeAction: Int? = null
    var editTextInputType: Int? = null
    var showRecycler: Boolean = false
    var items: List<Listable>? = null
    var onItemSelected: ((Listable, DialogFragment) -> Unit)? = null
    var itemsSelected: LiveData<List<Listable>>? = null
    var itemsAdapter: BaseAdapter<BaseAdapter.BaseViewHolder>? = null
    var itemsDivider: RecyclerView.ItemDecoration? = null
    var onPaginate: ((adapter: BaseAdapter<BaseAdapter.BaseViewHolder>, hasMoreData: () -> Unit) -> Unit)? = null
    var paginationAdvanceOffset: Int = 0
    var cancelable: Boolean = true
    var onCancel: (() -> Unit)? = null
    var onDismiss: (() -> Unit)? = null
    var widthFactor: Float? = null
    var heightFactor: Float? = null
}
