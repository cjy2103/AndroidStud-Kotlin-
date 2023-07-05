package com.example.listadapterdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listadapterdetail.R
import com.example.listadapterdetail.databinding.RecyclerViewListBinding
import com.example.listadapterdetail.util.DiffUtilCallback
import com.example.listadapterdetail.data.Character


class CharacterAdapter : ListAdapter<Character, CharacterAdapter.ViewHolder>(DiffUtilCallback()) {

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

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

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION){
                    listener?.onItemClick(getItem(position))
                }
            }
        }

        fun bind(character: Character) {
            binding.character = character
            binding.executePendingBindings()
        }
    }


}
