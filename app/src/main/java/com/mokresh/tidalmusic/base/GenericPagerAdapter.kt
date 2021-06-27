package com.mokresh.tidalmusic.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import com.dansdev.libeventpipe.EventPipe
import com.mokresh.tidalmusic.utils.UIEvent

abstract class GenericPagerAdapter<T : Any, B : ViewDataBinding>(
    @LayoutRes val layoutId: Int,
    val vm: BaseViewModel? = null,
) : PagingDataAdapter<T, GenericViewHolder<T, B>>(DiffUtilItemCallback<T>()), OnClickListener<T> {
    private var inflater: LayoutInflater? = null
    lateinit var pagerAdapterBinding: B

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T, B> {
        val layoutInflater = inflater ?: LayoutInflater.from(parent.context)
        pagerAdapterBinding = DataBindingUtil.inflate(layoutInflater, layoutId, parent, false)
        return GenericViewHolder(pagerAdapterBinding, this)
    }

    override fun onItemClick(item: T) {}

    override fun onBindViewHolder(holder: GenericViewHolder<T, B>, position: Int) {
        holder.bind(getItem(position), vm, position)
    }

    fun <T : UIEvent> publishUIEvent(event: T) {
        EventPipe.send(event)
    }


}
