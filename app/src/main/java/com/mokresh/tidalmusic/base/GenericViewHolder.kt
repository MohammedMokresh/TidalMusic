package com.mokresh.tidalmusic.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.mokresh.tidalmusic.BR

open class GenericViewHolder<T : Any, B : ViewDataBinding>(val binding: B, var clickListener: OnClickListener<T>) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: T?, vm: BaseViewModel?) {
        binding.setVariable(BR.item, item)
        binding.setVariable(BR.clickListener, clickListener)
        vm?.let { binding.setVariable(BR.vm, vm) }
        binding.executePendingBindings()
    }

    fun bind(item: T?, vm: BaseViewModel?, position: Int) {
        binding.setVariable(BR.item, item)
        binding.setVariable(BR.clickListener, clickListener)
        vm?.let { binding.setVariable(BR.vm, vm) }
        binding.setVariable(BR.position, position)
        binding.executePendingBindings()
    }
}

interface OnClickListener<T> {
    fun onItemClick(item: T)
}