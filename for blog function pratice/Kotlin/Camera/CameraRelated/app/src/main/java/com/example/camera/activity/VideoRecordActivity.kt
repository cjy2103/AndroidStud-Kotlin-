package com.example.camera.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.camera.core.Preview
import com.example.bottomnavigation.util.SystemUtil
import com.example.camera.databinding.ActivityVideoRecordingBinding
import com.example.camera.model.VideoRecordModel
import com.example.roomdb.util.LogUtils

class VideoRecordActivity : AppCompatActivity() {

    private var mBinding : ActivityVideoRecordingBinding? = null
    private val binding get() = mBinding!!
    private lateinit var videoRecordModel: VideoRecordModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()
        init()
        clickVideo()
        clickCancel()
    }

    private fun viewBinding(){
        mBinding = ActivityVideoRecordingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun init(){
        videoRecordModel = VideoRecordModel(this, this)
        SystemUtil.statusbarSetting(window)
        SystemUtil.sofNavigationBarHide(window)
    }

    fun surfaceProvider(preview: Preview) {
        preview.setSurfaceProvider(binding.previewVideo.surfaceProvider)
    }

    private fun clickVideo(){
        binding.btnRecord.setOnClickListener {
            if(binding.btnRecord.text.toString().equals("비디오 촬영")){
                binding.btnRecord.text = "비디오 촬영 종료"
                videoRecordModel.videoStart()
            } else {
                binding.btnRecord.text = "비디오 촬영"
                videoRecordModel.stop()
            }
        }
    }

    private fun clickCancel(){
        binding.btnCancel.setOnClickListener {
            finish()
        }
    }
}