package com.example.floatingbutton.vm

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.example.floatingbutton.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainViewModel {

    private var fabJazzAnimation = MutableLiveData<Animation>()
    fun getFabJazzAnimation() = fabJazzAnimation

    private lateinit var fabOpen : Animation
    private lateinit var fabClose : Animation

    private var isFapOpen = false

    fun init(context: Context){
        fabOpen = AnimationUtils.loadAnimation(context, R.anim.fab_open)
        fabClose = AnimationUtils.loadAnimation(context, R.anim.fab_close)

        fabJazzAnimation.value = fabClose
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
            fabView.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FFACF6FF"))
            fabJazzAnimation.value = fabClose
            isFapOpen = false
        } else {
            ObjectAnimator.ofFloat(fabView, View.ROTATION, 0f, 45f).start()
            fabView.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FF41A9FB"))
            fabJazzAnimation.value = fabOpen
            isFapOpen = true
        }
    }

    companion object{
        @BindingAdapter("animationStart")
        @JvmStatic
        fun fabJazzAnimation(fabView : FloatingActionButton, animation: MutableLiveData<Animation>){
            fabView.startAnimation(animation.value)
        }
    }

}
