package com.example.glide

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.example.glide.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).load("https://via.placeholder.com/300.png").placeholder(R.drawable.test)
            .dontAnimate()
            .into(binding.imageView)
//        val requestBuilder: RequestBuilder<Drawable> = Glide.with(this)
//            .asDrawable().sizeMultiplier(0.1f)
//
//        Glide.with(this).load(R.drawable.fail).
//        thumbnail(requestBuilder).into(binding.imageView)

    }
}