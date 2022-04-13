package com.example.customrecyclerview.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.customrecyclerview.R
import com.example.customrecyclerview.databinding.RecyclerViewListBinding

class CustomRecyclerAdapter(private val context : Context, private val activity : Activity)
    : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var binding : RecyclerViewListBinding
        fun viewBinding(){
            binding = RecyclerViewListBinding.bind(itemView)
        }
    }
}