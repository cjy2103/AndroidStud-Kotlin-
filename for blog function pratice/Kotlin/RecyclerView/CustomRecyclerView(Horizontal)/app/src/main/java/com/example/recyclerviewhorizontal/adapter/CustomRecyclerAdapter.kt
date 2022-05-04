package com.example.recyclerviewhorizontal.adapter

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerviewhorizontal.R
import com.example.recyclerviewhorizontal.databinding.RecyclerHorizontalBinding
import com.example.recyclerviewhorizontal.model.MyListItem

class CustomRecyclerAdapter() : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {

    private lateinit var context : Context
    private lateinit var activity : Activity
    private lateinit var myListItem : ArrayList<MyListItem>

    constructor(context: Context, activity: Activity, myListItem: ArrayList<MyListItem>) : this(){
        this.context = context
        this.activity = activity
        this.myListItem = myListItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_horizontal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imagePath = Uri.parse("android.resource://" + activity.packageName + "/"
            + myListItem[position].list[0].imagePath)
        Glide.with(context).load(imagePath).into(holder.binding.ivImage)
        holder.binding.tvTitle.text = myListItem[position].list[0].title
        holder.binding.tvDescribe.text = myListItem[position].list[0].describe
    }

    override fun getItemCount(): Int {
        return myListItem.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var mBinding : RecyclerHorizontalBinding? = null
        val binding get() = mBinding!!

        private fun viewBinding(){
            mBinding = RecyclerHorizontalBinding.bind(itemView)
        }

        init {
            viewBinding()
        }

    }

}