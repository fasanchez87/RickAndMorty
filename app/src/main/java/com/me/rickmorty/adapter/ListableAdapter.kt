package com.me.rickmorty.adapter

import androidx.lifecycle.LiveData
import com.me.rickmorty.R
import com.me.rickmorty.BR
import com.me.rickmorty.util.Idable

open class ListableAdapter(
    private val selected: LiveData<List<Idable>>?,
    private val listener: (Idable) -> Unit
) : BaseAdapter<BaseAdapter.BaseViewHolder>() {

    val listables = arrayListOf<Idable>()

    override fun getLayout(viewType: Int): Int = R.layout.dialog_listable_item

    override fun getItemCount(): Int {
        return listables.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.binding.setVariable(BR.item, listables[0])
        holder.binding.setVariable(BR.clickHandler, ClickHandler())
        holder.binding.executePendingBindings()
    }

    inner class ClickHandler {
        fun onListableClick(listable: Idable) {
            listener.invoke(listable)
        }
    }
}
