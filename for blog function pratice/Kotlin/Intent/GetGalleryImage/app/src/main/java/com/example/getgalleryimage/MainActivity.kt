package com.example.getgalleryimage

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.getgalleryimage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if(it.resultCode == RESULT_OK){
            if(it.data == null){
                return@registerForActivityResult
            }
            val uri = it.data!!.data
            Glide.with(this).load(uri).into(binding.ivImg)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        openGallery()
    }

    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun openGallery(){
        binding.btnSearch.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            resultLauncher.launch(intent)
        }
    }
}