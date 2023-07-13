package com.example.camera.activity

import android.os.Bundle
import com.example.camera.databinding.ActivityMainBinding
import com.example.camera.model.MainModel
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private lateinit var mainModel : MainModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        clickPicture()
        clickVideo()
    }

    private fun init(){
        mainModel = MainModel(this, this)
    }

    private fun clickPicture(){
        binding.btnPicture.setOnClickListener {
            mainModel.cameraPerMissionCheck()
        }
    }

    private fun clickVideo(){
        binding.btnVideo.setOnClickListener {
            mainModel.videoPerMissionCheck()
        }
    }

}