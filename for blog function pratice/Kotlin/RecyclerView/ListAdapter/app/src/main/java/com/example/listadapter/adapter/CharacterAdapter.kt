package com.example.listadapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listadapter.R
import com.example.listadapter.data.Character
import com.example.listadapter.databinding.RecyclerViewListBinding
import com.example.listadapter.util.DiffUtilCallback


class CharacterAdapter : ListAdapter<Character, CharacterAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RecyclerViewListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycler_view_list, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: RecyclerViewListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.character = character
            binding.executePendingBindings()
        }
    }


}
