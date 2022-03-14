package com.example.softbottomnavbarremove

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        softNavitationBarHide()
    }

    private fun softNavitationBarHide(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.setDecorFitsSystemWindows(false)
            if(window.insetsController != null){
                window.insetsController!!.hide(WindowInsets.Type.navigationBars())
                window.insetsController!!.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            window.decorView.systemUiVisibility =View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
    }
}