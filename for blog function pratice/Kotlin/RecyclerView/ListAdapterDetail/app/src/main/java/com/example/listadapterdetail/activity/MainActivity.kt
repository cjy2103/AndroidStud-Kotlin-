package com.example.listadapterdetail.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.listadapterdetail.R
import com.example.listadapterdetail.databinding.ActivityMainBinding
import com.example.listadapterdetail.vm.DetailViewModel
import com.example.listadapterdetail.vm.MainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding()

        characterListClick()
    }

    private fun dataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this
    }

    private fun characterListClick(){
        mainViewModel.characterClickListener.observe(this) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("title", it.title)
            intent.putExtra("describe", it.describe)
            intent.putExtra("image", it.image)
            intent.putExtra("youtube", it.youtubeLink)
            startActivity(intent)
        }
    }

}