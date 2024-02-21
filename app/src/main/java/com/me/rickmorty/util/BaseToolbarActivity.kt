package com.me.rickmorty.util

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.me.rickmorty.R
import com.me.rickmorty.databinding.ActivityBaseToolbarBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseToolbarActivity : BaseActivity(), BaseToolbarListener {

    private lateinit var scrollChangedListener: View.OnScrollChangeListener

    private var mScrollableParent: View? = null

    private val binding: ActivityBaseToolbarBinding by lazy {
        ActivityBaseToolbarBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@BaseToolbarActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        super.setContentView(binding.root)

        setBackground(R.color.color1)

        binding.toolbar.navigationIcon?.mutate()?.let {
            it.setTint(getColor(R.color.color_main))
            binding.toolbar.navigationIcon = it
        }
    }

    override fun setContentView(view: View?) {
        binding.flContainerBaseToolbar.addView(view)
    }

    override fun setContentView(layoutResID: Int) {
        View.inflate(this, layoutResID, binding.flContainerBaseToolbar)
    }

    open fun getScrollableParent(): View? = null

    private fun setScrollListener() {
        scrollChangedListener = View.OnScrollChangeListener { v, _, scrollY, _, _ ->
            mScrollableParent?.let { _ ->
                val toolbarSize = binding.toolbar.height
                if (scrollY + toolbarSize > toolbarSize || (v is RecyclerView && v.canScrollVertically(-1))) {
                    binding.toolbarSeparator.visibility = View.VISIBLE
                } else {
                    binding.toolbarSeparator.visibility = View.GONE
                }
            }
        }

        mScrollableParent?.setOnScrollChangeListener(scrollChangedListener)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                hideKeyboard()
                setResult(RESULT_OK)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun changeScrollListener(scrollView: View?) {
        mScrollableParent?.setOnScrollChangeListener(null)
        mScrollableParent = scrollView
        binding.toolbarSeparator.visibility = View.GONE
        setScrollListener()
    }

    override fun onStart() {
        super.onStart()
        mScrollableParent = getScrollableParent()
        setScrollListener()
    }

    override fun onStop() {
        super.onStop()
        mScrollableParent?.setOnScrollChangeListener(null)
    }

    private fun setBackground(@ColorRes color: Int) {
        val container = findViewById<View>(R.id.flContainerBaseToolbar)

        container.setBackgroundColor(ContextCompat.getColor(this, color))
    }

    override fun getToolbar() = binding.toolbar
}
