package com.example.camera.activity

import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.camera.databinding.ActivityPhotoResultBinding
import com.example.camera.model.PhotoCallback
import com.example.camera.model.PhotoResultModel

class PhotoResultActivity : AppCompatActivity() {

    private var mBinding : ActivityPhotoResultBinding? = null
    private val binding get() = mBinding!!
    private var photoResultModel = PhotoResultModel(this)
    private var imagePath : String? = null
    private lateinit var context : Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        init()

        clickOk()

        clickDelete()
    }

    private fun viewBinding(){
        mBinding = ActivityPhotoResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun init(){
        context = this
        imagePath = intent.getStringExtra("photoUri")
        val photoUri = Uri.parse(imagePath)
        Glide.with(this).load(photoUri).into(binding.ivPhoto)
    }

    private fun clickOk(){
        binding.btnOk.setOnClickListener {
            finish()
        }
    }

    private fun clickDelete(){
        binding.btnDelete.setOnClickListener {
            photoResultModel.deletePhoto(object : PhotoCallback{
                override fun deleteSuccess() {
                    Toast.makeText(context, "사진이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                override fun deleteFail() {
                    Toast.makeText(context, "사진 삭제에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }, imagePath)
        }
    }
}