package com.example.loadtxtfile

import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loadtxtfile.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        fileReadString()

        fileReadArray()
    }

    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun fileReadString(){
        val assetManager: AssetManager = assets
        val fileName = "sample.txt"

        val fileContent = FileReader.readTextFileString(assetManager, fileName)
        binding.tvWord.text = fileContent
    }

    private fun fileReadArray(){
        val assetManager: AssetManager = assets
        val fileName = "sample2.txt"

        val (nameArray, moneyArray) = FileReader.readTextFileArray(assetManager, fileName)

        binding.tvName.text = nameArray[2]
        binding.tvMoney.text = moneyArray[2]

    }
}