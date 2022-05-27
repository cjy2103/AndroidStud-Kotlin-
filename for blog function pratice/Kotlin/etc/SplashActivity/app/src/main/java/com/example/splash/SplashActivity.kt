package com.example.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bumptech.glide.Glide
import com.example.splash.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private var mBinding : ActivitySplashBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewBinding()

        imageSetting()

        moveMain()
    }

    private fun viewBinding(){
        mBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun imageSetting(){
        Glide.with(this).load(R.drawable.overme).into(binding.ivSplash)
    }

    private fun moveMain() {
        runOnUiThread {
            Handler(Looper.myLooper()!!).postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 2000)
        }
    }
}