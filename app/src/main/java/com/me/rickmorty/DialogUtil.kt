package com.me.rickmorty

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset
import java.util.*

fun showPopup(
    context: Context,
    fragmentManager: FragmentManager,
    title: String,
    description: String,
    cancelable: Boolean,
    onCancel: (() -> Unit)?,
    icon: Int?,
    image: String?,
    onDismiss: (() -> Unit)?
) = showPopup(
    context,
    fragmentManager,
    title,
    description,
    { _, _ -> },
    null,
    cancelable,
    onCancel,
    icon,
    image,
    onDismiss
)

fun showPopup(
    context: Context,
    fragmentManager: FragmentManager,
    title: String,
    description: String,
    positiveListener: (DialogInterface?, Int) -> Unit,
    positiveButton: String?,
    cancelable: Boolean,
    onCancel: (() -> Unit)?,
    icon: Int?,
    image: String?,
    onDismiss: (() -> Unit)?,
    builder: CustomDialogBuilder = CustomDialog.Builder(
        context
    )
) {
    val dialog = builder
        .setIcon(icon)
        .setImage(image)
        .setTitle(title)
        .setMessage(description)
        .setPositiveButton(positiveButton ?: context.getString(android.R.string.ok)) {
            positiveListener.invoke(null, 0)
        }
        .isCancelable(cancelable, onCancel)
        .setOnDismiss(onDismiss)
        .build()
    dialog.show(fragmentManager, null)
}

fun showPopup(
    context: Context,
    fragmentManager: FragmentManager,
    title: String,
    description: String,
    positiveListener: (DialogInterface?, Int) -> Unit,
    positiveButton: String?,
    negativeListener: ((DialogInterface?, Int) -> Unit)?,
    negativeButton: String?,
    cancelable: Boolean,
    onCancel: (() -> Unit)?,
    icon: Int?,
    image: String?,
    onDismiss: (() -> Unit)?,
    builder: CustomDialogBuilder = CustomDialog.Builder(
        context
    )
) {
    val dialog = builder
        .setIcon(icon)
        .setImage(image)
        .setTitle(title)
        .setMessage(description)
        .setPositiveButton(positiveButton ?: context.getString(android.R.string.ok)) {
            positiveListener.invoke(null, 0)
        }
        .setNegativeButton(negativeButton ?: context.getString(android.R.string.cancel)) {
            negativeListener?.invoke(null, 0)
        }
        .isCancelable(cancelable, onCancel)
        .setOnDismiss(onDismiss)
        .build()
    dialog.show(fragmentManager, null)
}

fun showPopupList(
    activity: Activity,
    fragmentManager: FragmentManager,
    title: String,
    list: List<Listable>,
    icon: Int?,
    image: String?,
    callback: ((Listable, DialogFragment) -> Unit)?,
    onDismiss: (() -> Unit)?,
    selected: LiveData<List<Listable>>?,
    hideButtons: Boolean,
    cancelable: Boolean,
    builder: CustomDialogBuilder = CustomDialog.Builder(
        activity
    )
) {
    activity.hideKeyboard()
    val dialog = builder
        .setTitle(title)
        .setIcon(icon)
        .setImage(image)
        .setItems(
            list,
            { listable, dialog -> callback?.invoke(listable, dialog) },
            selected,
            hideButtons
        )
        .isCancelable(cancelable)
        .setOnDismiss(onDismiss)
        .build()
    dialog.show(fragmentManager, null)
}

fun showPopupEdittext(
    context: Context,
    fragmentManager: FragmentManager,
    title: String,
    editTextListener: (String, TextView) -> Unit,
    initText: String?,
    hint: String?,
    imeAction: Int?,
    inputType: Int?,
    message: String?,
    cancelable: Boolean,
    okButton: String?,
    cancelButton: String?,
    onCancelAction: (() -> Unit)?,
    onDismiss: (() -> Unit)?,
    builder: CustomDialogBuilder = CustomDialog.Builder(
        context
    )
) {
    val dialog = builder
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(okButton ?: context.getString(android.R.string.ok))
        .setNegativeButton(cancelButton, null, onCancelAction)
        .isCancelable(cancelable)
        .enableEditText(hint, initText, imeAction, inputType, editTextListener)
        .setOnDismiss(onDismiss)
        .build()
    dialog.show(fragmentManager, null)
}

@Deprecated("Use LocalDate instead")
fun showDatePickerDialog(
    context: Context,
    date: Calendar,
    maxDate: Calendar? = null,
    minDate: Calendar? = null,
    callback: (Calendar) -> Unit
) {
    val dialog = DatePickerDialog(
        context,
        { _, year, month, day ->
            val newDate = Calendar.getInstance().apply {
                this.set(Calendar.YEAR, year)
                this.set(Calendar.MONTH, month)
                this.set(Calendar.DAY_OF_MONTH, day)
            }
            callback(newDate)
        },
        date.get(Calendar.YEAR),
        date.get(Calendar.MONTH),
        date.get(Calendar.DAY_OF_MONTH)
    )
    maxDate?.let { dialog.datePicker.maxDate = it.timeInMillis }
    minDate?.let { dialog.datePicker.minDate = it.timeInMillis }

    dialog.show()
}


fun showDatePickerPopup(
    context: Context,
    date: LocalDate = LocalDate.now(),
    maxDate: LocalDate? = null,
    minDate: LocalDate? = null,
    callback: (LocalDate) -> Unit
) {
    val dialog = DatePickerDialog(
        context,
        { _, year, month, day ->
            val newDate = LocalDate.of(year, month + 1, day)
            callback(newDate)
        },
        date.year,
        date.month.value - 1,
        date.dayOfMonth
    )
    maxDate?.let {
        dialog.datePicker.maxDate = it.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()
    }
    minDate?.let {
        dialog.datePicker.minDate = it.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()
    }

    dialog.show()
}

fun showTimePickerPopup(
    context: Context,
    time: LocalTime = LocalTime.now(),
    isTwentyFourHourView: Boolean = true,
    callback: (LocalTime) -> Unit
) {
    val dialog = TimePickerDialog(
        context,
        { _, hour, minute ->
            val newTime = LocalTime.of(hour, minute)
            callback(newTime)
        },
        time.hour,
        time.minute,
        isTwentyFourHourView
    )

    dialog.show()
}