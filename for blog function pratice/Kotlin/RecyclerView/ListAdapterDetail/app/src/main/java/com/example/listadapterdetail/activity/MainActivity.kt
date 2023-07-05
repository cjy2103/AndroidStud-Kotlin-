package com.example.listadapterdetail.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.listadapterdetail.R
import com.example.listadapterdetail.databinding.ActivityMainBinding
import com.example.listadapterdetail.vm.MainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val mainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding()

        characterListClick()
    }

    private fun dataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this
    }

    private fun characterListClick(){
        mainViewModel.characterClickListener.observe(this) {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }
    }

}