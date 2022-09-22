package com.example.gridrecyclerview.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.gridrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    companion object{
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
    }
    private var imageCallback = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        init()

        clickSelct()
    }

    override fun onResume() {
        super.onResume()
        if(imageCallback){
            val sharedPreferences = this.getSharedPreferences("image", MODE_PRIVATE)
            val path = sharedPreferences.getString("path","")
            if(!path!!.isEmpty()){
                val image = Uri.parse("android.resource://" + this.packageName + "/" + path)
                Glide.with(this).load(image).into(binding.ivImage)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        imageCallback = false
    }

    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun init(){
        context = this
    }

    private fun clickSelct(){
        binding.btnSelect.setOnClickListener {
            val intent = Intent(this, AlbumListActivity::class.java)
            startActivity(intent)
        }
    }

    fun albumSelectCallback(){
        imageCallback = true
    }
}