package com.me.rickmorty.adapter
import androidx.annotation.LayoutRes
import com.me.rickmorty.BaseClickHandler
import com.me.rickmorty.ItemViewable
import com.me.rickmorty.BR

open class AutoAdapter<T : ItemViewable>(
    @LayoutRes private var layout: Int? = null,
    data: MutableList<T> = mutableListOf(),
    private var onBindViewHolder: ((holder: BaseViewHolder, item: T, position: Int) -> Unit)? = null,
    private val clickHandler: BaseClickHandler<T>? = null
) : BaseDiffAdapter<BaseAdapter.BaseViewHolder, T>(data) {

    constructor(
        @LayoutRes layout: Int? = null,
        data: MutableList<T> = mutableListOf(),
        onBindViewHolder: ((holder: BaseViewHolder, item: T, position: Int) -> Unit)? = null,
        clickHandler: ((T) -> Unit)
    ) : this(
        layout,
        data,
        onBindViewHolder,
        object : BaseClickHandler<T> {
            override fun onItemClick(item: T) {
                clickHandler(item)
            }
        }
    )

    override fun getItemViewType(position: Int): Int {
        return layout ?: data[position].getLayout()
    }

    override fun getLayout(viewType: Int): Int = viewType

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = data[position]
        holder.binding.setVariable(BR.item, item)
        clickHandler?.let { holder.binding.setVariable(BR.clickHandler, it) }
        onBindViewHolder?.invoke(holder, item, position)
    }
}
