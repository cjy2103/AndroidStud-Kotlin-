package com.example.floatingbutton.vm

import android.animation.ObjectAnimator
import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.floatingbutton.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainViewModel {
    private lateinit var fabOpen : Animation
    private lateinit var fabClose : Animation

    private var isFapOpen = false

    private fun init(context: Context){
        fabOpen = AnimationUtils.loadAnimation(context, R.anim.fab_open)
        fabClose = AnimationUtils.loadAnimation(context, R.anim.fab_close)
    }

    fun clickFab(fabView : FloatingActionButton){
        animation(fabView)
    }

    fun clickFabJazz(fabView : FloatingActionButton){
        animation(fabView)
        fabView.setImageResource(R.drawable.uma_jazz)
    }

    fun clickFabDiyap(fabView : FloatingActionButton){
        animation(fabView)
        fabView.setImageResource(R.drawable.uma_diyap)
    }

    private fun animation(fabView: FloatingActionButton) {
        if(isFapOpen){
            ObjectAnimator.ofFloat(fabView, View.ROTATION, 45f,0f).start()

        }
    }

}
