package com.me.rickmorty.adapter

import androidx.lifecycle.LiveData
import com.me.rickmorty.Listable
import com.me.rickmorty.R
import com.me.rickmorty.BR

open class ListableAdapter(
    private val selected: LiveData<List<Listable>>?,
    private val listener: (Listable) -> Unit
) : BaseAdapter<BaseAdapter.BaseViewHolder>() {

    val listables = arrayListOf<Listable>()

    override fun getLayout(viewType: Int): Int = R.layout.dialog_listable_item

    override fun getItemCount(): Int {
        return listables.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.binding.setVariable(BR.item, listables[position])
        holder.binding.setVariable(BR.selected, selected)
        holder.binding.setVariable(BR.clickHandler, ClickHandler())
        holder.binding.executePendingBindings()
    }

    inner class ClickHandler {
        fun onListableClick(listable: Listable) {
            listener.invoke(listable)
        }
    }
}
