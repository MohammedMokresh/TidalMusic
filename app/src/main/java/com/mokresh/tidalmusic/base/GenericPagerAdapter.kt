package com.mokresh.tidalmusic.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter

abstract class GenericPagerAdapter<T : Any, B : ViewDataBinding>(
    @LayoutRes val layoutId: Int,
    val vm: BaseViewModel? = null,
    val clickListener: OnClickListener<T>
) : PagingDataAdapter<T, GenericViewHolder<T, B>>(DiffUtilItemCallback<T>()) {
    private var inflater: LayoutInflater? = null
    lateinit var pagerAdapterBinding: B

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T, B> {
        val layoutInflater = inflater ?: LayoutInflater.from(parent.context)
        pagerAdapterBinding = DataBindingUtil.inflate(layoutInflater, layoutId, parent, false)
        return GenericViewHolder(pagerAdapterBinding, clickListener)
    }

    override fun onBindViewHolder(holder: GenericViewHolder<T, B>, position: Int) {
        holder.bind(getItem(position), vm, position)
    }


}
