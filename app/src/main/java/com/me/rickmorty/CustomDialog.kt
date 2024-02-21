package com.me.rickmorty

import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.me.rickmorty.BR
import com.me.rickmorty.R
import com.me.rickmorty.adapter.BaseAdapter
import com.me.rickmorty.adapter.ListableAdapter

open class CustomDialog : DialogFragment(), CustomDialogable {

    companion object {
        private val DIALOG_MODEL = "dialog_model"
        private fun newInstance(customDialogModel: CustomDialogModel): CustomDialog {
            val dialog = CustomDialog()
            val args = Bundle()
            args.putParcelable(DIALOG_MODEL, customDialogModel)
            dialog.arguments = args
            return dialog
        }
    }

    protected val dialogModel: CustomDialogModel by lazy {
        arguments?.getParcelable(DIALOG_MODEL) ?: CustomDialogModel(getString(android.R.string.ok))
    }
    private val text = ObservableField("")

    private var customView: View? = null

    open lateinit var binding: ViewDataBinding

    protected var flDialog: FrameLayout? = null
    protected var rvDialog: RecyclerView? = null
    protected var etField: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_custom, container, false)
        binding.lifecycleOwner = this
        binding.setVariable(BR.clickHandler, ClickHandler())
        binding.setVariable(BR.dialogModel, dialogModel)
        binding.setVariable(BR.text, text)

        dialogModel.editTextInitText?.let { text.set(it) }

        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        context?.let {
            dialog?.window?.setBackgroundDrawable(
                ColorDrawable(
                    ContextCompat.getColor(
                        it,
                        android.R.color.transparent
                    )
                )
            )
        }

        val typedValue = TypedValue()
        var widthFactor = dialogModel.widthFactor
        var heightFactor = dialogModel.heightFactor

        if (widthFactor == null) {
            resources.getValue(R.dimen.dialog_width_factor, typedValue, true)
            widthFactor = typedValue.float
        }
        if (heightFactor == null) {
            resources.getValue(R.dimen.dialog_height_factor, typedValue, true)
            heightFactor = typedValue.float
        }

        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
        val layoutParams =
            dialog?.window?.decorView?.findViewById<View>(android.R.id.content)?.layoutParams
        layoutParams?.width =
            (
                when {
                    widthFactor < 0 -> widthFactor
                    else -> (width.toFloat() * widthFactor)
                }
                ).toInt()
        layoutParams?.height =
            (
                when {
                    heightFactor < 0 -> heightFactor
                    else -> (height.toFloat() * heightFactor)
                }
                ).toInt()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        flDialog = view.findViewById(R.id.flDialog)
        rvDialog = view.findViewById(R.id.rvDialog)
        etField = view.findViewById(R.id.etField)

        dialogModel.editTextImeAction?.let {
            etField?.setOnEditorActionListener { textView, actionId, keyEvent ->
                if (actionId == it) {
                    dialogModel.editTextListener?.invoke(text.get() ?: "", textView)
                    true
                } else {
                    false
                }
            }
        }

        if (dialogModel.showRecycler) {
            lateinit var adapter: BaseAdapter<BaseAdapter.BaseViewHolder>
            dialogModel.items?.let { listables ->
                adapter = ListableAdapter(dialogModel.itemsSelected) {
                    dialogModel.onItemSelected?.invoke(it, this)
                }.apply {
                    this.listables.addAll(listables)
                }
            }
            dialogModel.itemsAdapter?.let {
                adapter = it
            }
            rvDialog?.adapter = adapter
            rvDialog?.layoutManager = LinearLayoutManager(context)
            dialogModel.itemsDivider?.let { divider ->
                rvDialog?.addItemDecoration(
                    divider
                )
            }

            dialogModel.onPaginate?.let { hasMoreData ->
                val scrollListener = object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        if ((recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition() >= (recyclerView.adapter!!.itemCount - dialogModel.paginationAdvanceOffset)) {
                            recyclerView.removeOnScrollListener(this)
                            hasMoreData(adapter) {
                                recyclerView.addOnScrollListener(this)
                            }
                        }
                    }
                }
                rvDialog?.addOnScrollListener(scrollListener)
            }
        }

        if (customView != null) {
            flDialog?.removeAllViews()
            flDialog?.addView(customView)
            flDialog?.visibility = View.VISIBLE
        }

        isCancelable = dialogModel.cancelable
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        dialogModel.onCancel?.invoke()
    }

    override fun setView(view: View) {
        customView = view
    }

    override fun asDialogFragment() = this

    override fun onDismiss(dialog: DialogInterface) {
        dialogModel.onDismiss?.invoke()
        super.onDismiss(dialog)
    }

    class Builder(context: Context) : CustomDialogBuilder(context) {

        override fun buildAsCustomDialogable(): CustomDialog {
            return newInstance(dialogBuilderModel)
        }
    }

    inner class ClickHandler {

        fun onPositiveButtonClick() {
            dismiss()
            when {
                dialogModel.enableEditText -> {
                    etField?.let {
                        dialogModel.editTextListener?.invoke(text.get() ?: "", it)
                    }
                }
                else -> {
                    dialogModel.positiveButtonListener?.invoke()
                }
            }
        }

        fun onNegativeButtonClick() {
            dismiss()
            dialogModel.negativeButtonListener?.invoke()
        }

        fun onCloseButtonClick() {
            dismiss()
        }
    }
}
