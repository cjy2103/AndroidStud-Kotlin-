package com.example.listadapterdetail.util

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.listadapterdetail.data.Character

class DiffUtilCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.title == newItem.title
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem === newItem
    }


}