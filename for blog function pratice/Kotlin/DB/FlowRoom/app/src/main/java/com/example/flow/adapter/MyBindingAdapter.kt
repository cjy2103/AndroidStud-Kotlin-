package com.example.flow.adapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flow.R
import com.example.flow.data.Character
import com.example.flow.room.Data
import com.example.flow.util.ImagePath

class MyBindingAdapter {
    companion object{
        @BindingAdapter("setCharacter")
        @JvmStatic
        fun setItem(recyclerView: RecyclerView, list: List<Character>){
            if(recyclerView.adapter == null){
                val adapter = CharacterAdapter()
                adapter.submitList(list)
                recyclerView.adapter = adapter
            } else {
                val adapter = recyclerView.adapter as CharacterAdapter
                adapter.submitList(list)
            }
        }

        @BindingAdapter("app:srcCompat")
        @JvmStatic
        fun setImage(imageView: ImageView, imageKey : String) {
            val imagePath = ImagePath.imageLoad(imageKey)
            if(imagePath!= 0) {
                val drawable = ContextCompat.getDrawable(imageView.context, imagePath)
                imageView.setImageDrawable(drawable)
            }
        }

    }


}