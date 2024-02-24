package com.me.rickmorty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<T>() where T : BaseAdapter.BaseViewHolder {

    @LayoutRes
    abstract fun getLayout(viewType: Int): Int

    open fun <T : BaseViewHolder> getViewHolder(v: View): T = BaseViewHolder(v) as T

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T =
        getViewHolder(LayoutInflater.from(parent.context).inflate(getLayout(viewType), parent, false))

    override fun onViewAttachedToWindow(holder: T) {
        super.onViewAttachedToWindow(holder)
        holder.onAttach()
    }

    override fun onViewDetachedFromWindow(holder: T) {
        super.onViewDetachedFromWindow(holder)
        holder.onDetach()
    }

    class BaseViewHolder(v: View) : RecyclerView.ViewHolder(v), LifecycleOwner {
        val binding: ViewDataBinding = DataBindingUtil.bind(v)!!

        override val lifecycle: LifecycleRegistry = LifecycleRegistry(this)

        init {
            lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
            binding.lifecycleOwner = this
        }

        fun onAttach() {
            lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        }

        fun onDetach() {
            lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        }
    }
}
