package com.example.kakaomap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kakaomap.databinding.ActivityMainBinding
import com.example.kakaomap.util.HashKey
import com.example.kakaomap.util.LogUtil
import com.example.kakaomap.vm.MapViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val mapViewModel : MapViewModel = MapViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding()
    }

    private fun dataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = mapViewModel
        binding.lifecycleOwner = this
    }
}
