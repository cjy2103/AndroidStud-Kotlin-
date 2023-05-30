package com.example.asynclistdiffer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.asynclistdiffer.R
import com.example.asynclistdiffer.data.Character
import com.example.asynclistdiffer.databinding.RecyclerViewListBinding
import com.example.asynclistdiffer.util.DiffUtilCallback


class CharacterAdapter(list: ArrayList<Character>) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    private val asyncListDiffer: AsyncListDiffer<Character> =
        AsyncListDiffer(this, DiffUtilCallback())

    init {
        asyncListDiffer.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RecyclerViewListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycler_view_list, parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(asyncListDiffer.currentList[position])
    }

    fun submitList(newList: ArrayList<Character>) {
        asyncListDiffer.submitList(newList)
    }

    inner class ViewHolder(private val binding: RecyclerViewListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.character = character
            binding.executePendingBindings()
        }
    }


}
