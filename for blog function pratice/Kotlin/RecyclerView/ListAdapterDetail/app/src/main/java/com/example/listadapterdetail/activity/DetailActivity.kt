package com.example.listadapterdetail.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.listadapterdetail.R
import com.example.listadapterdetail.databinding.ActivityDetailBinding
import com.example.listadapterdetail.vm.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding
    private lateinit var viewModel : DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding()
        init()
    }

    private fun dataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun init(){
        val intent = intent
        viewModel.setData(intent)
    }
}