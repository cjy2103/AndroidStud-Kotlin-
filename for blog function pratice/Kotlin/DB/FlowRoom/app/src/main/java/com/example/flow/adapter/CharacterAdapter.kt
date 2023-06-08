package com.example.flow.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flow.R
import com.example.flow.data.Character
import com.example.flow.databinding.RecyclerViewListBinding
import com.example.flow.util.DiffUtilCallback


class CharacterAdapter : ListAdapter<Character,CharacterAdapter.ViewHolder>(DiffUtilCallback()) {

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

    inner class ViewHolder(private val binding: RecyclerViewListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(character: Character){
            binding.character = character
            binding.executePendingBindings()
        }
    }

}