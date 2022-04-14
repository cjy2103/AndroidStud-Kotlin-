package com.example.customrecyclerview.adapter

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.customrecyclerview.R
import com.example.customrecyclerview.databinding.RecyclerViewListBinding
import com.example.customrecyclerview.model.MyListItem

class CustomRecyclerAdapter(private val context: Context, private val activity: Activity,
                            private val myListItem: ArrayList<MyListItem>)
    : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imagePath = Uri.parse(
            "android.resource://" + activity.packageName + "/"
                    + myListItem[position].list[0].uri)
        Glide.with(context).load(imagePath).into(holder.binding.ivArt)
        holder.binding.tvTitle.text = myListItem[position].list[0].title
        holder.binding.tvDescribe.text = myListItem[position].list[0].describe

    }

    override fun getItemCount(): Int {
        return myListItem.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mBinding: RecyclerViewListBinding? = null
        val binding get() = mBinding!!
        private fun initBinding() {
            mBinding = RecyclerViewListBinding.bind(itemView)
        }

        init {
            initBinding()
        }
    }
}