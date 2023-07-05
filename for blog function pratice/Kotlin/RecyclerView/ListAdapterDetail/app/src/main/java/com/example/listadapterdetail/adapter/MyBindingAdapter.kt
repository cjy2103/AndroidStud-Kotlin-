package com.example.listadapterdetail.adapter

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listadapterdetail.data.Character

class MyBindingAdapter {
    companion object{
        @BindingAdapter("setCharacter", "clickListener")
        @JvmStatic
        fun setItems(recyclerView: RecyclerView, list: ArrayList<Character>, listener: OnItemClickListener) {
            if (recyclerView.adapter == null) {
                val adapter = CharacterAdapter()
                adapter.submitList(ArrayList(list))
                adapter.setOnItemClickListener(listener)
                recyclerView.adapter = adapter
            } else {
                val adapter = recyclerView.adapter as CharacterAdapter
                adapter.submitList(ArrayList(list))
            }
        }

        @BindingAdapter("app:srcCompat")
        @JvmStatic
        fun setImage(imageView: ImageView, drawable: Drawable?) {
            imageView.setImageDrawable(drawable)
        }
    }
}