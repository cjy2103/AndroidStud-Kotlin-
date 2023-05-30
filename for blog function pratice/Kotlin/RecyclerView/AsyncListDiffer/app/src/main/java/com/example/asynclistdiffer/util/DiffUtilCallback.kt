package com.example.asynclistdiffer.util

import androidx.recyclerview.widget.DiffUtil
import com.example.asynclistdiffer.data.Character

class DiffUtilCallback(private val oldList: ArrayList<Character>, private val newList: ArrayList<Character>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].describe == newList[newItemPosition].describe
    }

}