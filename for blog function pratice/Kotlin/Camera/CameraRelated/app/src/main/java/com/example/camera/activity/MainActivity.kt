package com.example.camera.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.camera.databinding.ActivityMainBinding
import com.example.camera.model.MainModel

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    private lateinit var mainModel : MainModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()
        init()
        clickPicture()
    }

    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun init(){
        mainModel = MainModel(this, this)
    }

    private fun clickPicture(){
        binding.btnPicture.setOnClickListener {
            mainModel.cameraPerMissionCheck()
        }
    }

}