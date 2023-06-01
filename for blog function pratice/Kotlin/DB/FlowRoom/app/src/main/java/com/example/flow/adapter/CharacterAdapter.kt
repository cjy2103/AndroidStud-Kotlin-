package com.example.flow.adapter


import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flow.data.Character
import com.example.flow.databinding.RecyclerViewListBinding
import com.example.listadapter.util.DiffUtilCallback

class CharacterAdapter : ListAdapter<Character,CharacterAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(private val binding: RecyclerViewListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(character: Character){

        }
    }

}