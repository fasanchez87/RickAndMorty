package com.me.rickmorty

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.io.File

@BindingAdapter("android:src", "defaultImage", "centerCrop", "circle", "size", "noCache", "glideListener", "curveBottomFactor", "isGif", requireAll = false)
fun loadUrlImage(
    imageView: ImageView,
    src: String?,
    @DrawableRes defaultImage: Int = 0,
    centerCrop: Boolean = true,
    circle: Boolean = false,
    size: Int? = null,
    noCache: Boolean = false,
    listener: RequestListener<Drawable>? = null,
    curveBottomFactor: Float? = null,
    isGif: Boolean = false
) {
    loadUrlImage(
        imageView,
        src,
        if (defaultImage != 0) ContextCompat.getDrawable(imageView.context, defaultImage) else null,
        centerCrop,
        circle,
        size,
        noCache,
        listener,
        curveBottomFactor,
        isGif
    )
}

@SuppressLint("CheckResult")
@BindingAdapter("android:src", "defaultImage", "centerCrop", "circle", "size", "noCache", "glideListener", "curveBottomFactor", "isGif", requireAll = false)
fun loadUrlImage(
    imageView: ImageView,
    src: String?,
    defaultImage: Drawable? = null,
    centerCrop: Boolean = true,
    circle: Boolean = false,
    size: Int? = null,
    noCache: Boolean = false,
    listener: RequestListener<Drawable>? = null,
    curveBottomFactor: Float? = null,
    isGif: Boolean = false
) {
    var defImage = defaultImage
    if (curveBottomFactor != null) {
        if (defImage != null) {
            defImage = CurveDrawable(defImage, curveBottomFactor)
        } else {
            defImage = CurveDrawable()
        }
    }

    if (src != null && src.isNotEmpty()) {
        val glideApp: RequestManager = Glide.with(imageView.context.applicationContext)

        if (isGif) glideApp.asGif()

//        val headers by KoinJavaComponent.inject(Headers::class.java)
        val requestCreator = when {
//            src.isUrl() -> glideApp.load(GlideUrl(src, headers))
            src.isUrl() -> glideApp.load(src)
            src.isFile() -> glideApp.load(File(src))
//            src.isFirebasePath() -> glideApp.load(FirebaseStorage.getInstance().getReference(src))
            else -> glideApp.load(File(src))
        }.addListener(listener)
        if (size != null)
            requestCreator.override(size)
        else if (imageView.width > 0 && imageView.height > 0) {
            requestCreator.override(imageView.width, imageView.height)
            if (centerCrop)
                requestCreator.centerCrop()
        }
        if (circle)
            requestCreator.circleCrop()
        if (noCache)
            requestCreator.diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(noCache)
        defImage?.let { requestCreator.error(defImage) }

        if (curveBottomFactor != null) {
            requestCreator.addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean = false
                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    resource?.let {
                        imageView.setImageDrawable(
                            CurveDrawable(
                                it,
                                curveBottomFactor
                            )
                        )
                        return true
                    } ?: return false
                }
            })
        }

        requestCreator.into(imageView)
    } else if (defImage != null) {
        imageView.setImageDrawable(defImage)
    } else {
        imageView.setImageDrawable(null)
    }
}

@BindingAdapter("android:src", "defaultImage", "centerCrop", "circle", "size", "noCache", "glideListener", "curveBottomFactor", "isGif", requireAll = false)
fun loadUrlImage(
    imageView: ImageView,
    src: File?,
    defaultImage: Drawable? = null,
    centerCrop: Boolean = true,
    circle: Boolean = false,
    size: Int? = null,
    noCache: Boolean = false,
    listener: RequestListener<Drawable>? = null,
    curveBottomFactor: Float? = null,
    isGif: Boolean = false
) {
    loadUrlImage(
        imageView,
        src?.absolutePath,
        defaultImage,
        centerCrop,
        circle,
        size,
        noCache,
        listener,
        curveBottomFactor,
        isGif
    )
}

@BindingAdapter("android:src", "defaultImage", "centerCrop", "circle", "size", "noCache", "glideListener", "curveBottomFactor", "isGif", requireAll = false)
fun loadUrlImage(
    imageView: ImageView,
    src: Drawable?,
    defaultImage: Drawable? = null,
    centerCrop: Boolean = true,
    circle: Boolean = false,
    size: Int? = null,
    noCache: Boolean = false,
    listener: RequestListener<Drawable>? = null,
    curveBottomFactor: Float? = null,
    isGif: Boolean = false
) {
    loadUrlImage(
        imageView,
        null as String?,
        src ?: defaultImage,
        centerCrop,
        circle,
        size,
        noCache,
        listener,
        curveBottomFactor,
        isGif
    )
}

@BindingAdapter("android:src", "defaultImage", "centerCrop", "circle", "size", "noCache", "glideListener", "curveBottomFactor", "isGif", requireAll = false)
fun loadUrlImage(
    imageView: ImageView,
    src: Int?,
    defaultImage: Drawable? = null,
    centerCrop: Boolean = true,
    circle: Boolean = false,
    size: Int? = null,
    noCache: Boolean = false,
    listener: RequestListener<Drawable>? = null,
    curveBottomFactor: Float? = null,
    isGif: Boolean = false
) {
    loadUrlImage(
        imageView,
        src?.let { ContextCompat.getDrawable(imageView.context, it) },
        defaultImage,
        centerCrop,
        circle,
        size,
        noCache,
        listener,
        curveBottomFactor,
        isGif
    )
}

@BindingAdapter("android:tint")
fun ImageView.setTint(color: Int?) {
    if (color != null) {
        try {
            imageTintList = ContextCompat.getColorStateList(context, color)
        } catch (e: Exception) {
            try {
                setColorFilter(ContextCompat.getColor(context, color))
            } catch (e: Exception) {
                setColorFilter(color)
            }
        }
    }
}
