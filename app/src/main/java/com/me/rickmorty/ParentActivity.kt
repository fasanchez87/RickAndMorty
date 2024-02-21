package com.me.rickmorty

import android.content.DialogInterface
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.me.rickmorty.app.App
import com.me.rickmorty.databinding.ActivityBaseParentBinding
import com.me.rickmorty.databinding.ActivityCharactersBinding
import org.koin.android.ext.android.inject
import java.net.UnknownHostException

abstract class ParentActivity : AppCompatActivity(), CoreListener {

    open val logger: BaseLogger by inject()

    abstract val navigator: CoreNavigator

    private lateinit var basebinding: ViewDataBinding
    private lateinit var flContainerBase: FrameLayout
    private lateinit var frmProgress: FrameLayout

    private val binding by lazy {
        ActivityBaseParentBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@ParentActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(binding.root)

        if (App.SCREEN_WIDTH == 0 && App.SCREEN_HEIGHT == 0) {
            getScreenSize()
        }

        flContainerBase = findViewById(R.id.flContainerBase)
        frmProgress = findViewById(R.id.frmProgress)
    }

    private fun getScreenSize() {
        val size = Point()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = windowManager.currentWindowMetrics
            val insets = windowMetrics.windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            size.x = windowMetrics.bounds.width() - insets.left - insets.right
            size.y = windowMetrics.bounds.height() - insets.top - insets.bottom
        }
        else {
            @Suppress("DEPRECATION")
            windowManager.defaultDisplay.getSize(size)
        }
        App.SCREEN_HEIGHT = size.y
        App.SCREEN_WIDTH = size.x
    }

    override fun setContentView(view: View?) {
        flContainerBase.addView(view)
    }

    override fun setContentView(layoutResID: Int) {
        View.inflate(this, layoutResID, flContainerBase)
    }

    override fun onError(throwable: Throwable) {
        handleError(throwable) {}
    }

    open fun handleError(throwable: Throwable, onDismiss: () -> Unit) {
        throwable.printStackTrace()
        hideLoading()
        when (throwable) {
            is ErrorLoginException -> {
                //navigator.navigateToLogin()?.let { startActivity(it) }
            }
            is ShowMessageException ->
                showPopup(
                    throwable.title ?: resources.getString(R.string.error),
                    throwable.text,
                    icon = null,
                    onDismiss = onDismiss
                )
            is UnknownHostException -> showPopup(
                getString(R.string.error),
                getString(R.string.no_internet),
                icon = null,
                onDismiss = onDismiss
            )
            else -> onErrorDefaultCase(throwable, onDismiss)
        }
    }

    open fun onErrorDefaultCase(throwable: Throwable, onDismiss: () -> Unit) {
        if (hasNetworkConnection().not()) {
            showPopup(
                getString(R.string.error),
                getString(R.string.no_internet),
                icon = null,
                onDismiss = onDismiss
            )
        } else {
            showPopup(
                getString(R.string.error),
                getString(R.string.unexpected_error),
                icon = null,
                onDismiss = onDismiss
            )
        }
    }

    fun getResultEventObserver(
        action: () -> Unit,
        actionError: ((Throwable, _super: (Throwable) -> Unit) -> Unit)? = null
    ) = object : ResultEventObserver(this) {

            override fun onSuccess() {
                action.invoke()
            }
            override fun onError(ex: Throwable) {
                actionError?.let {
                    it(ex) { super.onError(it) }
                } ?: super.onError(ex)
            }
        }

    fun <T> getResultObjectObserver(
        action: (T) -> Unit,
        actionEmpty: (() -> Unit)? = null,
        actionError: ((Throwable, _super: (Throwable) -> Unit) -> Unit)? = null
    ) = object : ResultObserver<T>(this) {

        override fun onReceived(t: T) {
            action.invoke(t)
        }

        override fun onError(ex: Throwable) {
            actionError?.let {
                it(ex) { super.onError(it) }
            } ?: super.onError(ex)
        }

        override fun onEmpty() {
            actionEmpty?.invoke()
        }
    }

    fun getResultEventEmptyObserver(hideLoading: Boolean = true) =
        object : ResultEventObserver(this) {
            override fun onSuccess() {
                if (hideLoading) hideLoading()
            }
        }

    override fun showLoading() {
        frmProgress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        frmProgress.visibility = View.GONE
    }

    override fun showPopup(
        title: String,
        description: String,
        cancelable: Boolean,
        onCancel: (() -> Unit)?,
        icon: Int?,
        image: String?,
        onDismiss: (() -> Unit)?
    ) = showPopup(
        this,
        supportFragmentManager,
        title,
        description,
        cancelable,
        onCancel,
        icon,
        image,
        onDismiss
    )

    override fun showPopup(
        title: String,
        description: String,
        positiveListener: (DialogInterface?, Int) -> Unit,
        positiveButton: String?,
        cancelable: Boolean,
        onCancel: (() -> Unit)?,
        icon: Int?,
        image: String?,
        onDismiss: (() -> Unit)?
    ) = showPopup(
        this,
        supportFragmentManager,
        title,
        description,
        positiveListener,
        positiveButton,
        cancelable,
        onCancel,
        icon,
        image,
        onDismiss
    )

    override fun showPopup(
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
        onDismiss: (() -> Unit)?
    ) = showPopup(
        this,
        supportFragmentManager,
        title,
        description,
        positiveListener,
        positiveButton,
        negativeListener,
        negativeButton,
        cancelable,
        onCancel,
        icon,
        image,
        onDismiss
    )
}
