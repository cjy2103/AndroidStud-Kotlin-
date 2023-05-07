package com.example.camera.activity

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.camera.R
import com.example.camera.databinding.ActivityPhotoResultBinding

class PhotoResultActivity : AppCompatActivity() {

    private var mBinding : ActivityPhotoResultBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        init()

        clickOk()
    }

    private fun viewBinding(){
        mBinding = ActivityPhotoResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun init(){
        val imagePath = intent.getStringExtra("photoUri")
        val photoUri = Uri.parse(imagePath)
        Glide.with(this).load(photoUri).into(binding.ivPhoto)
    }

    private fun clickOk(){
        binding.btnOk.setOnClickListener {
            finish()
        }
    }
}