package com.example.camera.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.Preview
import com.example.bottomnavigation.util.SystemUtil
import com.example.camera.databinding.ActivityCameraBinding
import com.example.camera.model.CameraModel

class CameraActivity : AppCompatActivity() {

    private var mBinding : ActivityCameraBinding? = null
    private val binding get() = mBinding!!

    private lateinit var cameraModel : CameraModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()
        init()
        clickPhoto()
        clickCancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraModel.destroy()
    }

    private fun viewBinding(){
        mBinding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun init(){
        SystemUtil.statusbarSetting(window)
        SystemUtil.sofNavigationBarHide(window)

        cameraModel = CameraModel(this, this)
    }

    fun surfaceProvider(preview: Preview) {
        preview.setSurfaceProvider(binding.previewPhoto.surfaceProvider)
    }

    private fun clickPhoto(){
        binding.btnPhoto.setOnClickListener {
            cameraModel.capturePhoto()
        }
    }

    private fun clickCancel(){
        binding.btnCancel.setOnClickListener {
            finish()
        }
    }


}