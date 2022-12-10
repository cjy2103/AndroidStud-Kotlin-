package com.example.camera.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.camera.databinding.ActivityCameraBinding

class CameraActivity : AppCompatActivity() {

    private var mBinding : ActivityCameraBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()
    }

    private fun viewBinding(){
        mBinding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}