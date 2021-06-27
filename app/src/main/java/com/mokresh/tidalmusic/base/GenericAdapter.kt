package com.mokresh.tidalmusic.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dansdev.libeventpipe.EventPipe
import com.mokresh.tidalmusic.BR
import com.mokresh.tidalmusic.utils.UIEvent


abstract class GenericAdapter<T : Any, B : ViewDataBinding>(
    @LayoutRes val layoutId: Int,
    val vm: BaseViewModel? = null,
    private var items: List<T?>? = listOf()
) :
    RecyclerView.Adapter<GenericViewHolder<T, B>>(), OnClickListener<T> {

    private var inflater: LayoutInflater? = null
    lateinit var adapterBinding: B

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T, B> {
        val layoutInflater = inflater ?: LayoutInflater.from(parent.context)
        adapterBinding = DataBindingUtil.inflate(layoutInflater, layoutId, parent, false)
        return GenericViewHolder(adapterBinding, this)
    }

    override fun onItemClick(item: T) {}

    override fun getItemCount(): Int = items?.size!!

    override fun onBindViewHolder(holder: GenericViewHolder<T, B>, position: Int) {
        items?.let {
            val item = it[position]
            holder.bind(item, vm, position)
        }
    }


    fun setItems(newData: List<T?>?) {
        val recipeDiffUtil = BaseDiffUtil(items, newData)
        val diffUtilResult = DiffUtil.calculateDiff(recipeDiffUtil)
        items = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }

    fun <T : UIEvent> publishUIEvent(event: T) {
        EventPipe.send(event)
    }

    fun getItems(): List<T?>? {
        return items
    }


}

