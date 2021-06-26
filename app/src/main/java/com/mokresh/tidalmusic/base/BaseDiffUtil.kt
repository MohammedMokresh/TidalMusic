package com.mokresh.tidalmusic.base

import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtil<T>(private val oldData: List<T?>?, private val newData: List<T?>?) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return if (oldData.isNullOrEmpty()){
            0
        }else{
            oldData.size
        }
    }


    override fun getNewListSize(): Int {
        return if (newData.isNullOrEmpty()){
            0
        }else{
            newData.size
        }
    }


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData === newData
    }


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData == newData
    }
}
