package com.example.loadtxtfile

import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loadtxtfile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        fileRead()
    }

    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun fileRead(){
        val assetManager: AssetManager = assets
        val fileName = "sample.txt"

        val fileContent = FileReader.readTextFileString(assetManager, fileName)
        binding.tvWord.text = fileContent
    }
}