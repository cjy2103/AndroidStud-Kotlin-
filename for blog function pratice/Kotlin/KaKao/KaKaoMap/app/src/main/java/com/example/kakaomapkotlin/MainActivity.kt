package com.example.kakaomapkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kakaomapkotlin.vm.MapViewModel
import com.example.kakaomapkotlin.databinding.ActivityMainBinding
import com.example.kakaomapkotlin.util.HashKey

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val mapViewModel : MapViewModel = MapViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding()
//        val hashKey = HashKey()
//        hashKey.migrateSignatures(this)
    }

    private fun dataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = mapViewModel
        binding.lifecycleOwner = this
        mapViewModel.init(this,binding.mapView)
    }
}
