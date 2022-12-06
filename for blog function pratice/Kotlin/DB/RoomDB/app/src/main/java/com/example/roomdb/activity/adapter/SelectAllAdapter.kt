package com.example.roomdb.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.R
import com.example.roomdb.activity.dto.DataDTO
import com.example.roomdb.databinding.RecyclerSelectAllItemBinding

class SelectAllAdapter(private val context : Context, private val list : ArrayList<DataDTO>)
    : RecyclerView.Adapter<SelectAllAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_select_all_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvTitle.text = list[position].title
        holder.binding.tvMsg.text = list[position].msg
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var mBinding: RecyclerSelectAllItemBinding? = null
        val binding get() = mBinding!!

        private fun viewBinding(){
            mBinding = RecyclerSelectAllItemBinding.bind(itemView)
        }

        init {
            viewBinding()
        }
    }
}