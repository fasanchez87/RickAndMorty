package com.me.rickmorty.util

import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.me.rickmorty.R
import com.me.rickmorty.app.App.Companion.get

class CurveDrawable(val bitmap: Bitmap?, val radiusFactor: Float = 8f) : Drawable() {

    constructor(drawable: Drawable, radiusFactor: Float = 8f) : this(drawable.toBitmap(), radiusFactor)
    constructor(radiusFactor: Float = 8f) : this(ContextCompat.getDrawable(get(), R.color.color00)!!.toBitmap(), radiusFactor)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()

    override fun draw(canvas: Canvas) {
        bitmap?.let { paint.shader = BitmapShader(it, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP) }
        paint.style = Paint.Style.FILL
//        paint.color = ContextCompat.getColor(get(), R.color.color1)

        path.reset()
        val bounds = getBounds()

        val horizontalOffset = bounds.width() / radiusFactor
        val top = -bounds.height().toFloat()
        val bottom = bounds.height().toFloat()
        val left = -horizontalOffset
        val right = bounds.width() + horizontalOffset

        val ovalRect = RectF(left, top, right, bottom)

        canvas.drawArc(ovalRect, 0f, 180f, true, paint)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun getOpacity(): Int = PixelFormat.TRANSLUCENT

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }
}
