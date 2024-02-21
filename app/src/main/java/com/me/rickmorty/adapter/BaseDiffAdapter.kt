package com.me.rickmorty.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.me.rickmorty.Idable

abstract class BaseDiffAdapter<T : BaseAdapter.BaseViewHolder, K : Idable>(
    open var data: MutableList<K> = mutableListOf(),
    open val headersCount: Int = 0
) : BaseAdapter<T>() {

    override fun getItemCount(): Int = data.size + headersCount

    open fun update(newItems: List<K>) {
        val diffResult = DiffUtil.calculateDiff(BaseDiffItems(newItems, data))
        data = ArrayList(newItems)
        diffResult.dispatchUpdatesTo(object : ListUpdateCallback {
            override fun onInserted(position: Int, count: Int) {
                notifyItemRangeInserted(position + headersCount, count)
            }

            override fun onRemoved(position: Int, count: Int) {
                notifyItemRangeRemoved(position + headersCount, count)
            }

            override fun onMoved(fromPosition: Int, toPosition: Int) {
                notifyItemMoved(fromPosition + headersCount, toPosition + headersCount)
            }

            override fun onChanged(position: Int, count: Int, payload: Any?) {
                notifyItemRangeChanged(position + headersCount, count, payload)
            }
        })
    }

    open inner class BaseDiffItems(
        private val newItems: List<Idable>,
        private val oldItems: List<Idable>
    ) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            compareIdsDiff(oldItems, oldItemPosition, newItems, newItemPosition)

        override fun getOldListSize() = oldItems.size
        override fun getNewListSize() = newItems.size
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            compareItemsDiff(oldItems, oldItemPosition, newItems, newItemPosition)
    }

    open fun compareIdsDiff(
        oldItems: List<Idable>,
        oldItemPosition: Int,
        newItems: List<Idable>,
        newItemPosition: Int
    ) = oldItems[oldItemPosition].id == newItems[newItemPosition].id

    open fun compareItemsDiff(
        oldItems: List<Idable>,
        oldItemPosition: Int,
        newItems: List<Idable>,
        newItemPosition: Int
    ) = oldItems[oldItemPosition] == newItems[newItemPosition]
}
