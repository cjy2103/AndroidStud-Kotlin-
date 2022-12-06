package com.example.viewpager2.adapter

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.viewpager2.R
import com.example.viewpager2.dto.ListItem
import com.example.viewpager2.databinding.Viewpager2ItemBinding

class ViewPager2Adapter(private val context : Context, private val activity : Activity,
                        private val listItem : ArrayList<ListItem>)
    : RecyclerView.Adapter<ViewPager2Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.viewpager2_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imagePath = Uri.parse("android.resource://"+activity.packageName+"/"+listItem[position].list[0].imagePath)
        Glide.with(context).load(imagePath).into(holder.binding.imageView)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var mBinding : Viewpager2ItemBinding? = null
        val binding get() = mBinding!!

        private fun viewBinding(){
            mBinding = Viewpager2ItemBinding.bind(itemView)
        }

        init {
            viewBinding()
        }

    }
}