package com.me.rickmorty

import android.R
import android.content.Context
import android.text.SpannableStringBuilder
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.me.rickmorty.adapter.BaseAdapter

abstract class CustomDialogBuilder(context: Context) {

    protected val dialogBuilderModel = CustomDialogModel(context.getString(R.string.ok))

    fun setIcon(@DrawableRes icon: Int?) = apply {
        dialogBuilderModel.icon = icon
    }

    fun setImage(image: String?) = apply {
        dialogBuilderModel.image = image
    }

    fun setTitle(title: String?) = apply {
        dialogBuilderModel.title = title
    }

    fun setTitle(title: SpannableStringBuilder?) = apply {
        dialogBuilderModel.title = title
    }

    fun setMessage(message: String?) = apply {
        dialogBuilderModel.message = message
    }

    fun setMessage(message: SpannableStringBuilder?) = apply {
        dialogBuilderModel.message = message
    }

    fun setPositiveButton(
        button: String,
        positiveButtonContentDescription: String? = null,
        positiveButtonListener: (() -> Unit)? = null
    ) = apply {
        dialogBuilderModel.positiveButton = button
        dialogBuilderModel.positiveButtonListener = positiveButtonListener
        dialogBuilderModel.positiveButtonContentDescription = positiveButtonContentDescription
    }

    fun setNegativeButton(
        button: String?,
        negativeButtonContentDescription: String? = null,
        negativeButtonListener: (() -> Unit)? = null
    ) = apply {
        dialogBuilderModel.negativeButton = button
        dialogBuilderModel.negativeButtonListener = negativeButtonListener
        dialogBuilderModel.negativeButtonContentDescription = negativeButtonContentDescription
    }

    fun hideButtons() = apply {
        dialogBuilderModel.hideButtons = true
    }

    fun hideClose() = apply {
        dialogBuilderModel.hideClose = true
    }

    fun setCloseContentDescription(closeContentDescription: String?) = apply {
        dialogBuilderModel.closeContentDescription = closeContentDescription
    }

    /**
     * @param imeAction {@link EditorInfo#imeOptions}
     * @param inputType {@link InputType}
     */
    fun enableEditText(
        hint: String? = null,
        initText: String? = null,
        imeAction: Int? = null,
        inputType: Int? = null,
        editTextListener: ((String, TextView) -> Unit)
    ) = apply {
        dialogBuilderModel.editTextHint = hint
        dialogBuilderModel.editTextInitText = initText
        dialogBuilderModel.enableEditText = true
        dialogBuilderModel.editTextListener = editTextListener
        imeAction?.let {
            if (it != EditorInfo.IME_ACTION_UNSPECIFIED &&
                it != EditorInfo.IME_ACTION_NONE &&
                it != EditorInfo.IME_ACTION_GO &&
                it != EditorInfo.IME_ACTION_SEARCH &&
                it != EditorInfo.IME_ACTION_SEND &&
                it != EditorInfo.IME_ACTION_NEXT &&
                it != EditorInfo.IME_ACTION_DONE &&
                it != EditorInfo.IME_ACTION_PREVIOUS
            ) {
                throw IllegalArgumentException("Action debe ser un IME_ACTION de EditorInfo")
            }
            dialogBuilderModel.editTextImeAction = it
        }
        inputType?.let { dialogBuilderModel.editTextInputType = inputType }
    }

    fun setItems(
        items: List<Listable>,
        onItemSelected: (Listable, DialogFragment) -> Unit,
        selected: LiveData<List<Listable>>? = null,
        hideButtons: Boolean = true
    ) = apply {
        dialogBuilderModel.showRecycler = true
        dialogBuilderModel.items = items
        dialogBuilderModel.onItemSelected = onItemSelected
        dialogBuilderModel.itemsSelected = selected
        if (hideButtons) hideButtons()
    }

    fun setItems(
        adapter: BaseAdapter<BaseAdapter.BaseViewHolder>,
        decorator: RecyclerView.ItemDecoration? = null,
        hideButtons: Boolean = true
    ) = apply {
        dialogBuilderModel.showRecycler = true
        dialogBuilderModel.itemsAdapter = adapter
        dialogBuilderModel.itemsDivider = decorator
        if (hideButtons) hideButtons()
    }

    fun setItemsPagination(
        onPaginate: ((adapter: BaseAdapter<BaseAdapter.BaseViewHolder>, hasMoreData: () -> Unit) -> Unit)?,
        advanceOffset: Int = 6
    ) = apply {
        dialogBuilderModel.onPaginate = onPaginate
        dialogBuilderModel.paginationAdvanceOffset = advanceOffset
    }

    fun isCancelable(
        cancelable: Boolean,
        onCancelListener: (() -> Unit)? = null
    ) = apply {
        dialogBuilderModel.cancelable = cancelable
        dialogBuilderModel.onCancel = onCancelListener
    }

    fun setOnDismiss(onDismiss: (() -> Unit)? = null) = apply {
        dialogBuilderModel.onDismiss = onDismiss
    }

    fun setWidthFactor(width: Float) = apply {
        dialogBuilderModel.widthFactor = width
    }

    fun setHeightFactor(height: Float) = apply {
        dialogBuilderModel.heightFactor = height
    }

    // Pasamos de la interfaz al DialogFragment
    fun build(): DialogFragment = buildAsCustomDialogable().asDialogFragment()

    // Por si necesitamos construirlo obteniendo la interfaz para, por ejemplo, hacer un setView o cualquier método de la misma.
    abstract fun buildAsCustomDialogable(): CustomDialogable
}
