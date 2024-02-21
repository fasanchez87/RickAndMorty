package com.me.rickmorty.util

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.BindingAdapter
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import java.text.DecimalFormat

@BindingAdapter("layout_width", "layout_height", requireAll = false)
fun setWidthAndHeight(view: View, width: Float?, height: Float?) {
    val layoutParams = view.layoutParams
    width?.let { layoutParams.width = it.toDp(view.context.resources.displayMetrics).toInt() }
    height?.let { layoutParams.height = it.toDp(view.context.resources.displayMetrics).toInt() }
    view.layoutParams = layoutParams
}


fun Float.format(decimals: Int): String {
    var d = ""
    for (i in 1..decimals) {
        d += "#"
    }
    return DecimalFormat("#.$d").format(this)
}

fun Float.toDp(displayMetrics: DisplayMetrics) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, displayMetrics)


@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean?) = if (value != null && value) {
    view.visibility = View.VISIBLE
} else {
    view.visibility = View.GONE
}

@BindingAdapter("selected")
fun setSelected(view: View, selected: Boolean) {
    view.isSelected = selected
}

@BindingAdapter("android:enabled")
fun setEnabled(view: View, enabled: Boolean) {
    view.isEnabled = enabled
}

@BindingAdapter("app:backgroundTint")
fun View.setBackgroundTint(color: ColorStateList) {
    this.backgroundTintList = color
}

@BindingAdapter("activated")
fun setActivated(view: View, activated: Boolean) {
    view.isActivated = activated
}

@BindingAdapter("android:layout_margin", "android:layout_marginLeft", "android:layout_marginRight", "android:layout_marginTop", "android:layout_marginBottom", "android:layout_marginStart", "android:layout_marginEnd", requireAll = false)
fun setMargins(view: View, margin: Float?, left: Float?, right: Float?, top: Float?, bottom: Float?, start: Float?, end: Float?) {
    view.layoutParams = (view.layoutParams as ViewGroup.MarginLayoutParams).apply {
        with(view.context.resources.displayMetrics) {
            (margin ?: left)?.let { leftMargin = it.toDp(this).toInt() }
            (margin ?: top)?.let { topMargin = it.toDp(this).toInt() }
            (margin ?: right)?.let { rightMargin = it.toDp(this).toInt() }
            (margin ?: bottom)?.let { bottomMargin = it.toDp(this).toInt() }
            (margin ?: start)?.let { marginStart = it.toDp(this).toInt() }
            (margin ?: end)?.let { marginEnd = it.toDp(this).toInt() }
        }
    }
}

@BindingAdapter("android:padding", "android:paddingLeft", "android:paddingRight", "android:paddingTop", "android:paddingBottom", "android:paddingStart", "android:paddingEnd", requireAll = false)
fun setPaddings(view: View, padding: Float?, left: Float?, right: Float?, top: Float?, bottom: Float?, start: Float?, end: Float?) {
    with(view.context.resources.displayMetrics) {
        view.setPaddingRelative(
            (padding ?: start ?: left)?.toDp(this)?.toInt() ?: view.paddingStart,
            (padding ?: top)?.toDp(this)?.toInt() ?: view.paddingTop,
            (padding ?: end ?: right)?.toDp(this)?.toInt() ?: view.paddingEnd,
            (padding ?: bottom)?.toDp(this)?.toInt() ?: view.paddingBottom
        )
    }
}

@BindingAdapter("app:layout_constraintWidth_percent")
fun setConstraintWidthPercent(view: View, percent: Float) {
    if (view.parent !is ConstraintLayout) return

    val set = ConstraintSet()
    set.clone(view.parent as ConstraintLayout)
    set.constrainPercentWidth(view.id, percent)
    set.applyTo(view.parent as ConstraintLayout)
}

@BindingAdapter("app:layout_constraintHeight_percent")
fun setConstraintHeightPercent(view: View, percent: Float) {
    if (view.parent !is ConstraintLayout) return

    val set = ConstraintSet()
    set.clone(view.parent as ConstraintLayout)
    set.constrainPercentHeight(view.id, percent)
    set.applyTo(view.parent as ConstraintLayout)
}

@BindingAdapter("app:layout_constraintVertical_bias")
fun setConstraintVerticalBias(view: View, bias: Float) {
    if (view.parent !is ConstraintLayout) return

    val set = ConstraintSet()
    set.clone(view.parent as ConstraintLayout)
    set.setVerticalBias(view.id, bias)
    set.applyTo(view.parent as ConstraintLayout)
}

@BindingAdapter("app:layout_constraintHorizontal_bias")
fun setConstraintHorizontalBias(view: View, bias: Float) {
    if (view.parent !is ConstraintLayout) return

    val set = ConstraintSet()
    set.clone(view.parent as ConstraintLayout)
    set.setHorizontalBias(view.id, bias)
    set.applyTo(view.parent as ConstraintLayout)
}

@BindingAdapter("animate_layout_constraintEnd_toEndOf")
fun setAnimateConstraintEnd_End(view: View, id: Int) {
    if (view.parent is ConstraintLayout) {
        val constraintLayout = view.parent as ConstraintLayout
        val cs = ConstraintSet()
        cs.clone(constraintLayout)
        cs.connect(view.id, ConstraintSet.END, id, ConstraintSet.END)
        val transition = AutoTransition()
        transition.duration = 100
        TransitionManager.beginDelayedTransition(constraintLayout, transition)
        cs.applyTo(constraintLayout)
    }
}

@BindingAdapter("animate_layout_constraintStart_toStartOf")
fun setAnimateConstraintStart_Start(view: View, id: Int) {
    if (view.parent is ConstraintLayout) {
        val constraintLayout = view.parent as ConstraintLayout
        val cs = ConstraintSet()
        cs.clone(constraintLayout)
        cs.connect(view.id, ConstraintSet.START, id, ConstraintSet.START)
        val transition = AutoTransition()
        transition.duration = 100
        TransitionManager.beginDelayedTransition(constraintLayout, transition)
        cs.applyTo(constraintLayout)
    }
}

fun View.asBitmap(): Bitmap {
    layoutParams = ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    measure(
        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    )
    layout(0, 0, measuredWidth, measuredHeight)

    val bitmap =
        Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    layout(left, top, right, bottom)
    draw(canvas)

    return bitmap
}
