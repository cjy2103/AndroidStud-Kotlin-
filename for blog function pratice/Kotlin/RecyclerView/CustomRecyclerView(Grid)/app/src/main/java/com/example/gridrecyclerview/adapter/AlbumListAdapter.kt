package com.example.gridrecyclerview.adapter

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gridrecyclerview.R
import com.example.gridrecyclerview.databinding.RecyclerGridBinding

class AlbumListAdapter(private val context: Context, private val activity: Activity,
                       private val imageList : ArrayList<String>)
: RecyclerView.Adapter<AlbumListAdapter.ViewHolder>(){

    private var mListener : OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_grid, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imagePath = Uri.parse("android.resource://" + activity.packageName + "/"
                + imageList[position])

        Glide.with(context).load(imagePath).into(holder.binding.imageView)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var mBinding : RecyclerGridBinding? = null
        val binding get() = mBinding!!

        init {
            viewBinding()
            itemClick()
        }


        private fun viewBinding(){
            mBinding = RecyclerGridBinding.bind(itemView)
        }

        private fun itemClick(){
            itemView.setOnClickListener {
                val pos = bindingAdapterPosition
                if(pos != RecyclerView.NO_POSITION){
                    mListener!!.onItemClick( it!!, pos)
                }
            }
        }
    }

}